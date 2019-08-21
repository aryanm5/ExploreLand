package general;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import items.Item;


public class ToolBar {
	public static String selectedItem = "Empty";
	public ArrayList<Slot> slots = new ArrayList<Slot>();
	public static LinkedHashMap<String, ArrayList<Item>> items = new LinkedHashMap<String, ArrayList<Item>>();
	public int numOfSlots = 52;//26 before 2 rows
	
	public ToolBar() {
		int slotY = Global.app.getHeight() - 60;
		for(int i = 0; i < numOfSlots/2; ++i) {
			slots.add(new Slot("Empty", "", (i+1)*60 + (Global.app.getWidth()/2 - numOfSlots/4*60), slotY));
		}
		slotY = Global.app.getHeight() - 120;
		for(int i = numOfSlots/2; i < numOfSlots; ++i) {
			slots.add(new Slot("Empty", "", (i-numOfSlots/2+1)*60 + (Global.app.getWidth()/2 - numOfSlots/4*60), slotY));
		}
		giveItem("items.IronIngot", 10);
		giveItem("items.GoldIngot", 10);
		giveItem("blocks.OakLog", 10);
		giveItem("blocks.BirchLog", 10);
		giveItem("blocks.Cobblestone", 30);
		giveItem("items.Coal", 30);
		giveItem("blocks.IronOre", 20);
		giveItem("blocks.GoldOre", 20);
		giveItem("items.Diamond", 20);
		giveItem("blocks.Sand", 20);
		giveItem("blocks.Gravel", 20);
		giveItem("items.GoldenPickaxe", 100);
		giveItem("blocks.Torch", 50);
		giveItem("items.GoldenShovel", 5);
		giveItem("items.Apple", 10);
		giveItem("blocks.Water", 10);
 	}
	
	public static void giveItem(String item, int amount) {
		if(!items.containsKey(item)) {
			items.put(item, new ArrayList<Item>());
		}
		for(int i = 0; i < amount; ++i) {
			if(item.split("\\.")[0].equals("items")) {
				items.get(item).add(Global.stringToItem(item, 0, 0));
			} else {
				items.get(item).add(Global.blockToItem(item, 0, 0));
			}
		}
	}
	
	public static void giveItem(String item) {
		giveItem(item, 1);
	}
	public static void giveItem(Item item) {
		if(!items.containsKey(item.type)) {
			items.put(item.type, new ArrayList<Item>());
		}
		items.get(item.type).add(item);
	}
	
	public static boolean hasItem(String item, int amount) {
		if(items.containsKey(item)) {
			if(items.get(item).size() >= amount) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean hasItem(String item) {
		return hasItem(item, 1);
	}
	
	public static void removeItem(String item, int amount) {
		if(amount >= items.get(item).size()) {
			items.remove(item);
		} else {
			items.get(item).subList(items.get(item).size()-amount, items.get(item).size()).clear();
		}
	}
	
	public static void removeFirstItem(String item, int amount) {
		if(amount >= items.get(item).size()) {
			items.remove(item);
		} else {
			items.get(item).subList(0, amount-1).clear();
		}
	}
	public static void mouseReleased(int button, int x, int y) {
		if(!selectedItem.equals("Empty")) {
			items.get(selectedItem).get(0).stoppedUse();
		}
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < numOfSlots; ++i) {
			slots.get(i).draw(g);
		}
	}
	
	public void update(Input input) {
		for(int i = items.size(); i < slots.size(); ++i) {
			slots.get(i).setItem("Empty");
		}
		int temp = 0;
		for(String key : items.keySet()) {
			slots.get(temp).setItem(key);
			temp++;
		}
		
		for(int i = 0; i < numOfSlots; ++i) {
			slots.get(i).update(input);
		}
		if(!hasItem(selectedItem)) {
			selectedItem = "Empty";
		}
		
		if(input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			if(!selectedItem.equals("Empty")) {
				items.get(selectedItem).get(0).isBeingUsed();
			}
		}
		
		if(input.isKeyPressed(Input.KEY_1)) { setSelectedItem(slots.get(0).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_2)) { setSelectedItem(slots.get(1).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_3)) { setSelectedItem(slots.get(2).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_4)) { setSelectedItem(slots.get(3).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_5)) { setSelectedItem(slots.get(4).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_6)) { setSelectedItem(slots.get(5).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_7)) { setSelectedItem(slots.get(6).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_8)) { setSelectedItem(slots.get(7).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_9)) { setSelectedItem(slots.get(8).itemHeld); }
		else if(input.isKeyPressed(Input.KEY_0)) { setSelectedItem(slots.get(slots.size()-1).itemHeld); }
	}
	
	public void setSelectedItem(String newItem) {
		selectedItem = newItem;
	}
	
	public Item getLastItemInSlot(String item) {
		return items.get(item).get(items.get(item).size()-1);
	}
	
	public class Slot {
		public String itemHeld;
		Image image;
		public int x;
		public int y;
		public boolean isHovered = false;
        
		
		Slot(String item, String newImagePath, int newX, int newY) {
			itemHeld = item;
			x = newX;
			y = newY;
		}
		
		public void update(Input input) {
			if(itemHeld != "Empty") {
				if(items.containsKey(itemHeld) == false) {
					if(ToolBar.selectedItem == itemHeld) {
						ToolBar.selectedItem = "Empty";
					}
					setItem("Empty");
				} else {
					if(!hasItem(itemHeld)) {
						items.remove(itemHeld);
					}
				}
				
			}
			int xpos = input.getMouseX();
			int ypos = input.getMouseY();

			
			if(xpos > x && xpos < x + 50 && ypos > y && ypos < y + 50) {
				isHovered = true;
				
				if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					Global.toolbar.setSelectedItem(itemHeld);
				}
				
			} else if(isHovered == true){
				isHovered = false;
			}
			
			if(selectedItem == itemHeld) {
				int dWheel = Mouse.getDWheel();
				if(dWheel > 0) {
					if(Global.toolbar.slots.indexOf(this) - 1 >= 0) {
						Global.toolbar.setSelectedItem(Global.toolbar.slots.get(Global.toolbar.slots.indexOf(this) - 1).itemHeld);
					} else {
						Global.toolbar.setSelectedItem(Global.toolbar.slots.get(Global.toolbar.slots.size()-1).itemHeld);
					}
				} else if(dWheel < 0) {
					if(Global.toolbar.slots.indexOf(this) + 1 < Global.toolbar.slots.size()) {
						Global.toolbar.setSelectedItem(Global.toolbar.slots.get(Global.toolbar.slots.indexOf(this) + 1).itemHeld);
					} else {
						Global.toolbar.setSelectedItem(Global.toolbar.slots.get(0).itemHeld);
					}
				}
			}
		}
		
		public void draw(Graphics g) {
			if(!itemHeld.equals("Empty")) {
				g.setColor(Color.gray);
				g.fillRect(x - 1, y - 1, 52, 52);
				g.setColor(Color.white);
				if(selectedItem == itemHeld) {
					g.setColor(Color.red);
				} else if(isHovered == true) {
					g.setColor(Color.orange);
				}
				g.fillRect(x, y, 50, 50);
			} else {
				if(!selectedItem.equals("Empty")) {
					g.setColor(Color.darkGray);
					g.drawRect(x-1, y-1, 52, 52);
				} else {
					g.setColor(new Color(1, 0, 0, 0.5f));
					g.fillRect(x, y, 50, 50);
				}
			}
			
			if(!itemHeld.equals("Empty") && hasItem(itemHeld)) {
				float oldRot = items.get(itemHeld).get(0).image.getRotation();
				items.get(itemHeld).get(0).image.setRotation(0);
				items.get(itemHeld).get(0).image.draw(x + 5, y + 5, 50-10, 50-10);
				items.get(itemHeld).get(0).image.setRotation(oldRot);
				g.setColor(Color.green);
				g.drawString(ToolBar.items.get(itemHeld).size() + "", x + 10, y + 20);
				
				//draw durability bar
				Item firstItem = items.get(itemHeld).get(0);
				if(firstItem.hasDurability && firstItem.durability < firstItem.maxDurability) {
					Color barColor = new Color(255-(int) ((double) (firstItem.durability)/firstItem.maxDurability*255), (int) ((double) (firstItem.durability)/firstItem.maxDurability*255), 0);
					g.setColor(Color.lightGray);
					g.fillRect(x + 3, y+38, 44, 9);
					g.setColor(barColor);
					g.fillRect(x + 5, y+40, (float) ((double) (firstItem.durability)/firstItem.maxDurability*40), 5);
				}
				if(firstItem.isEdible && firstItem.amountEaten > 0) {
					Color barColor = new Color(255-(int) ((double) (firstItem.amountEaten)/firstItem.totalEat*255), (int) ((double) (firstItem.amountEaten)/firstItem.totalEat*255), 0);
					g.setColor(Color.lightGray);
					g.fillRect(x + 3, y+38, 44, 9);
					g.setColor(barColor);
					g.fillRect(x + 5, y+40, (float) ((double) (firstItem.amountEaten)/firstItem.totalEat*40), 5);
				}
				if(isHovered == true) {
					g.setColor(Color.white);
					g.drawString(itemHeld.split("\\.")[1], x-10, y-20);
				}
			}
		}
		
		public void setItem(String item) {
			if(item == "Empty") {
				if(itemHeld == ToolBar.selectedItem) {
					ToolBar.selectedItem = "Empty";
				}
			}
			itemHeld = item;
		}
	}
}
