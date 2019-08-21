package blocks;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import entities.FallingBlock;
import general.Coord;
import general.Crafting;
import general.Global;
import general.Smelting;
import general.ToolBar;

//All types of blocks extend this
public class Block {
	public int x;
	public int y;
	public int xPixel;
	public int yPixel;
	public int width = 30;
	public int height = 30;
	public float zoom = 1.875f;
	public boolean isHovered = false;
	public String type;
	public Image image;
	public String imagePath;
	public double amountBroken;
	public int breakTime = 1000;
	public boolean blocksPlayer = true;
	public String drop = "";
	public boolean isSmeltable = false;
	public String smeltOutput = "";
	public int smeltAmount = 0;
	public float brightness = 0f;
	public boolean light = false;
	public boolean transparent = false;
	public float transparency = 1f;
	public boolean falls = false;
	public boolean drownsMobs = false;
	public LinkedHashMap<String, Double> breakSpeeds = new LinkedHashMap<String, Double>() {{
		put("Empty", 1.0);
	}};
	public ArrayList<String> dropsWithTool = new ArrayList<String>() {{
		add("Everything");
	}};
	
	public Block(int xCoord, int yCoord) {
		x = xCoord;
		y = yCoord;
		xPixel = Coord.toPixels(xCoord);
		yPixel = Coord.toPixels(29-yCoord);
	}
	
	//Allow for Map or something for blockSettings, like {blockPlayer: false, dropsItem: false, etc.}
	public Block(int xCoord, int yCoord, boolean blockPlayer) {
		this(xCoord, yCoord);
		blocksPlayer = blockPlayer;
	}
	public void setImage() {
		try {
			image = new Image(imagePath);
			image.setFilter(Image.FILTER_NEAREST);
			image.setImageColor(brightness, brightness, brightness, transparency);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drop = type;
	}
	
	public void update(Input input) {
		int xpos = input.getMouseX();
		int ypos = input.getMouseY();
		
		setBrightness(0f);
		int r = Global.settings.get("BrightRadius");
		for(int dx = x-r; dx <= x+r; ++dx) {
			if(dx < 0 || dx >= Global.world.width) { continue; }
			for(int dy = y-r; dy <= y+r; ++dy) {
				if(dy < 0 || dy >= Global.world.height) { continue; }
				if(Global.getBlock(dx, dy).light) {
					double newBrightness = 0;
					if(Math.abs(x - dx) <= 1 && Math.abs(y - dy) <= 1) {
						newBrightness = 1;
					} else if(Math.abs(x - dx) <= 2 && Math.abs(y - dy) <= 2) {
						newBrightness = 0.8;
					} else if(Math.abs(x - dx) <= 3 && Math.abs(y - dy) <= 3) {
						newBrightness = 0.6;
					} else if(Math.abs(x - dx) <= 4 && Math.abs(y - dy) <= 4){
						newBrightness = 0.4;
					} else {
						newBrightness = 0.3;
					}
					if(brightness < newBrightness) {
						setBrightness(newBrightness);
					}
				}
			}
		}
		
		double playerDistance = Math.sqrt((Math.pow((Math.abs(x-Global.player.x)), 2) + Math.pow((Math.abs(y-Global.player.y)), 2)));
		if(playerDistance <= Global.player.brightRange) {
			double newBrightness = 3/playerDistance;
			if(type == "blocks.Air") { newBrightness /= 1.3f; };
			if(brightness < newBrightness) {
				setBrightness(newBrightness);
			}
		}
		
		if(Math.abs(x-Global.player.x) <= 5 && Math.abs(y-Global.player.y) <= 5) {
			if(xpos > xPixel && xpos < xPixel + 30 && ypos > yPixel && ypos < yPixel + 30) {
				isHovered = true;
				
				if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					isBeingMined();
				} else {
					amountBroken = 0;
					if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
						try { isRightClicked(); } catch(Exception e) { e.printStackTrace(); }
					}
				}
				
			} else if(isHovered == true)  {
				amountBroken = 0;
				isHovered = false;
			}
		}
		
		if(falls) {
			if(x >= 0 && x < Global.world.width && y > 0 && y < Global.world.height) {
				if(!Global.getBlock(x, y-1).blocksPlayer) {
					Global.entities.add(new FallingBlock(x, y, type));
					Global.blocks.get(x).set(y, new Air(x, y));
				}
			}
		}
	}
	
	public void setBrightness(float newBrightness) {
		if(brightness != newBrightness) {
			brightness = newBrightness;
			image.setImageColor(brightness, brightness, brightness);
		}
	}
	public void setBrightness(double newBrightness) {
		setBrightness((float) newBrightness);
	}
	
	public void breakBlock() {
		if(ToolBar.selectedItem != "Empty") {
			ToolBar.items.get(ToolBar.selectedItem).get(0).loseDurability();
		}
		if(dropsWithTool.contains(ToolBar.selectedItem) || dropsWithTool.contains("Everything")) {
			if(drop.startsWith("blocks")) {
				Global.items.add(Global.blockToItem(drop, x + Math.random()*5/10.0, y - Math.random()*5/10.0));
			} else {
				Global.items.add(Global.stringToItem(drop, x + Math.random()*5/10.0, y - Math.random()*5/10.0));
			}
			if(Crafting.isOpen) {
				Global.crafter.openButtons();
			}
			if(Smelting.isOpen) {
				Global.smelter.openButtons();
			}
		}
		
		Global.player.addExhaustion(0.005);
		Global.blocks.get(x).set(y, new Air(x, y));
		Global.blocks.get(x).get(y).image.setFilter(Image.FILTER_NEAREST);
	}
	
	public void addDropsWithTools(String[] tools) {
		for(String tool : tools) {
			dropsWithTool.add(tool);
		}
		dropsWithTool.remove("Everything");
	}
	
	public void isBeingMined() {
		if(breakSpeeds.containsKey(ToolBar.selectedItem)) {
			amountBroken += 10/breakSpeeds.get(ToolBar.selectedItem); //divide 10 by it to turn it into number of seconds
		} else {
			amountBroken += 10/breakSpeeds.get("Empty");
		}
		
		if(amountBroken >= breakTime) {
			breakBlock();
		}
	}
	
	public void isRightClicked() throws Exception {}
	public void isThrown() {}
	public void isPlaced() {}
	public boolean checkIfHovered(int xpos, int ypos) {
		if(xpos > xPixel && xpos < xPixel + 30 && ypos > yPixel && ypos < yPixel + 30) {
			return true;
		} else {
			return false;
		}
	}
	
}
