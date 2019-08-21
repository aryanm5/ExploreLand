package entities;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import general.Coord;
import general.Global;
import general.ToolBar;
import items.Item;

public class Player extends Mob {
	public String name;
	public int brightRange = 5;
	public double foodLevel = 20;
	public double exhaustion = 0;
	public boolean isSprinting = false;
	public double distanceFallen;
	public double saturation = 5;
	
	public Player(int x, int y) {
		super(x, y);
		setMaxHealth(20);
		imagePath = Global.playerImagePath;
		name = Global.playerName;
		width = 30;
		height = 60;
		setImage();
	}
	
	public void update(Input input) {
		xVelocity = 0;
		//Remember that +y means up, and -y means down.
		//Global.getBlock((int) (Global.player.x + 0.5), (int) (Global.player.y+0.5)) IS THE BLOCK WITH STEV'S FEET!!
		if(input.isKeyPressed(Input.KEY_Q) && !ToolBar.selectedItem.equals("Empty")) {
			if(!input.isKeyDown(Input.KEY_LCONTROL)) {
				final Item throwItem = ToolBar.items.get(ToolBar.selectedItem).get(0);
				throwItem.x = x + 0.5;
				throwItem.y = y + 1;
				ToolBar.items.get(throwItem.type).remove(throwItem);
				int xpos = input.getMouseX();
				int ypos = input.getMouseY();
				HashMap<String, Double> vector = Global.normalize(xpos - (Coord.toPixels(30) + width/2), ypos - (Coord.toPixels(15) - height/2));
				throwItem.xVelocity = vector.get("x")/8;
				throwItem.yVelocity = vector.get("y")/5;
				throwItem.canBePickedUp = false;
				throwItem.startNoPickup = System.currentTimeMillis();
				Global.items.add(throwItem);
			} else {
				while(ToolBar.hasItem(ToolBar.selectedItem) && !ToolBar.selectedItem.equals("Empty")) {
					final Item throwItem = ToolBar.items.get(ToolBar.selectedItem).get(0);
					throwItem.x = x + 0.5;
					throwItem.y = y + 1;
					throwItem.image.setRotation((float) (Math.random()*100));
					ToolBar.items.get(throwItem.type).remove(throwItem);
					int xpos = input.getMouseX();
					int ypos = input.getMouseY();
					HashMap<String, Double> vector = Global.normalize(xpos - (Coord.toPixels(30) + width/2), ypos - (Coord.toPixels(15) - height/2));
					throwItem.xVelocity = vector.get("x")/8;
					throwItem.yVelocity = vector.get("y")/5;
					throwItem.canBePickedUp = false;
					throwItem.startNoPickup = System.currentTimeMillis();
					Global.items.add(throwItem);
				}
			}
		}
		
		if(x >= 0 && x < Global.world.width-1 && y >= 0 && y < Global.world.height - 1.5) {
			if(!Global.getBlock((int) (x + 0.5), (int) (y - yVelocity)).blocksPlayer && y > 0) {
				if(Global.getBlock((int) (x + 0.5), (int) y).type.equals("blocks.Water")) {
					distanceFallen = 0;
					if(yVelocity > 0.03) {
						yVelocity -= 0.001;
					} else if(yVelocity < 0) {
						yVelocity += 0.00001;
					}
					yVelocity += 0.0005;
				} else {
					yVelocity += 0.003;
				}
			} else {
				yVelocity = 0;
				if((int) distanceFallen > 3) {
					//fall damage
					reduceHealth((int) distanceFallen - 3);
				}
				distanceFallen = 0;
				if(input.isKeyDown(Input.KEY_SPACE)) {
					if(Global.getBlock((int) (x + 0.5), (int) (y + 0.5)).type.equals("blocks.Water")) {
						yVelocity = -0.05;
					} else {
						yVelocity = -0.11; //-0.01
					}
					if(isSprinting) {
						addExhaustion(0.2);
					} else {
						addExhaustion(0.05);//0.05
					}
				}
			}
			if(Global.getBlock((int) (x + 0.5), (int) y).type.equals("blocks.Water") && input.isKeyDown(Input.KEY_SPACE)) {
				if(yVelocity > -0.05) {
					yVelocity += -0.001;
				}
			}
			int tempStopSprint = 0;
			if (input.isKeyDown(Input.KEY_D) && !Global.getBlock((int) (x+1), (int) (y+0.5)).blocksPlayer && !Global.getBlock((int) (x+1), (int) (y+1.5)).blocksPlayer && x < Global.world.width - 1.15) {
			    if(foodLevel >= 6 && input.isKeyDown(Input.KEY_LCONTROL)) {
			    	isSprinting = true;
			    }
			    
			    if(foodLevel >= 6 && isSprinting) {
			    	xVelocity = 0.08; //0.56
			    	addExhaustion(0.005);
				} else {
					xVelocity = 0.05; //travels 1/20 of a block
				}
			} else { tempStopSprint++; }
			if(input.isKeyDown(Input.KEY_A) && !Global.getBlock((int) (x), (int) (y+0.5)).blocksPlayer && !Global.getBlock((int) (x), (int) (y+1.5)).blocksPlayer && x > 0.15) {
				if(foodLevel >= 6 && input.isKeyDown(Input.KEY_LCONTROL)) {
					isSprinting = true;
			    }
				if(foodLevel >= 6 && isSprinting) {
					xVelocity = -0.08;
			    	addExhaustion(0.005);
				} else {
			    	xVelocity = -0.05;
			    }
			} else { tempStopSprint++; }
			if(tempStopSprint == 2) { isSprinting = false; }
			if(y + 2 < Global.world.height && Global.getBlock((int) (x + 0.5), (int) (y+2)).blocksPlayer) {
				yVelocity = 0.01;
			}
		} else {
			yVelocity += 0.003;
			if (input.isKeyDown(Input.KEY_D)) {
				xVelocity = 0.1;
			}
			if(input.isKeyDown(Input.KEY_A)) {
				xVelocity = -0.1;
			}
		}
		
		x += xVelocity;
		y -= yVelocity;
		distanceFallen += yVelocity;
		
		if(foodLevel >= 20 && health < 20 && saturation > 0) {
			if(Global.time % 50 == 0) {
				addHealth(1);
				addExhaustion(6);
			}
		} else if(foodLevel >= 18 && health < 20 ) {
			if(Global.time % 400 == 0) {
				addHealth(1);
				addExhaustion(6);
			}
		} else if(foodLevel <= 0 && health > 1) {
			if(Global.time % 400 == 0) {
				reduceHealth(1);
			}
		}
		updateOxygen();
		if(y < 0 && Global.time % 50 == 0) {
			reduceHealth(2);
		}
	}
	public void draw(Graphics g) {
		image.draw(Coord.toPixels(30), Coord.toPixels(15) - height, width, height);
		g.setColor(Color.black);
		g.drawString(name, Coord.toPixels(29.8), Coord.toPixels(14.2) - height);
		drawBars(g);
	}
	public void drawBars(Graphics g) {
		//hunger
		for(int i = 2; i <= 20; i += 2) {
			if(i > foodLevel+1) {
				Global.noLeg.draw((int) (Global.app.getWidth()*2.0/3 - i*16), Global.app.getHeight() - 160, 30, 30);// - 100
			} else if(i - foodLevel == 1) {
				Global.halfLeg.draw((int) (Global.app.getWidth()*2.0/3 - i*16), Global.app.getHeight() - 160, 30, 30);
			} else {
				Global.leg.draw((int) (Global.app.getWidth()*2.0/3 - i*16), Global.app.getHeight() - 160, 30, 30);
			}
		}
		
		//health
		for(int i = 2; i <= maxHealth; i += 2) {
			if(i > health+1) {
				Global.noHeart.draw((int) (Global.app.getWidth()/4 + i*16), Global.app.getHeight() - 160, 30, 30);
			} else if(i - health == 1) {
				Global.halfHeart.draw((int) (Global.app.getWidth()/4 + i*16), Global.app.getHeight() - 160, 30, 30);
			} else {
				Global.heart.draw((int) (Global.app.getWidth()/4 + i*16), Global.app.getHeight() - 160, 30, 30);
			}
		}
		
		//oxygen
		if(oxygen < maxOxygen) {
			for(int i = 1; i <= oxygen; i += 1) {
				Global.bubble.draw((int) (Global.app.getWidth()/4 + i*32), Global.app.getHeight() - 200, 30, 30);// - 140
			}
		}
	}
	@Override
	public boolean submerged() {
		if((int) (x+0.5) >= 0 && (int) (x+0.5) < Global.world.width && (int) (y+1.5) >= 0 && (int) (y + 1.5) < Global.world.height) {
			return (Global.getBlock((int) (x+0.5), (int) (y + 1.5)).drownsMobs);
		}
		return false;
	}
	public void addSaturation(double amount) {
		saturation += amount;
		if(saturation > foodLevel) { saturation = foodLevel; }
	}
	public void reduceSaturation(double amount) {
		saturation -= amount;
		if(saturation < 0) { saturation = 0; }
	}
	public void addExhaustion(double amount) {
		exhaustion += amount;
		while(exhaustion >= 4) {
			reduceFood(1); exhaustion -= 4;
		}
	}
	public void reduceFood(int amount) {
		if(saturation <= 0) {
			foodLevel -= amount;
		} else {
			reduceSaturation(amount);
		}
		if(foodLevel <= 0) { foodLevel = 0; }
		if(saturation > foodLevel) { saturation = foodLevel; }
	}
	
	public void addFood(int amount) {
		foodLevel += amount;
		if(foodLevel >= 20) {
			foodLevel = 20;
		}
	}
	
}
