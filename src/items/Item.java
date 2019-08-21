package items;

import org.newdawn.slick.Input;

import entities.Entity;
import general.Global;
import general.ToolBar;

public class Item extends Entity {
	public String type;
	public String itemType;
	public boolean hasDurability = false;
	public int durability = 0;
	public int maxDurability = 0;
	public boolean canBePickedUp = true;
	public long startNoPickup;
	public boolean isEdible = false;
	public int amountEaten = 0;
	public int totalEat = 350;
	public int state;
	
	public Item(double x, double y) {
		super(x, y);
		width = 15;
		height = 15;
	}
	
	
	public void update(Input input) {
		super.update(input);
		image.rotate(0.5f);
		
		if(xVelocity > 0) {
			//xVelocity -= 0.001;//0.003
			if(xVelocity < 0) {
				xVelocity = 0;
			}
		} else {
			//xVelocity += 0.001;
			if(xVelocity > 0) {
				xVelocity = 0;
			}
		}
		
		if(!canBePickedUp) {
			if(System.currentTimeMillis() - startNoPickup >= 2000) {
				canBePickedUp = true;
			}
		}
		if(canBePickedUp && x - 1 < Global.player.x && x + 1 > Global.player.x && y - 2 < Global.player.y && y + 1 > Global.player.y) {
			ToolBar.giveItem(this);
			Global.items.remove(this);
		}
	}
	
	public void isBeingUsed() {
	}
	public void stoppedUse() {
	}
	
	public void setDurability(int dur) {
		hasDurability = true;
		maxDurability = dur;
		durability = dur;
	}
	
	public void loseDurability() {
		if(hasDurability) {
			durability--;
			if(durability <= 0) {
				breakItem();
			}
		}
	}
	
	public void breakItem() {
		ToolBar.items.get(type).remove(ToolBar.items.get(type).indexOf(this));
	}
}