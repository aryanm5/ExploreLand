package items;

import general.Global;
import general.ToolBar;

//food items extend this
public class Food extends Item {
	public int fill;
	public double saturation;
	
	public Food(double x, double y) {
		super(x, y);
		isEdible = true;
	}
	
	@Override
	public void isBeingUsed() {
		if(Global.player.foodLevel < 20) {
			Global.player.isSprinting = false;
			amountEaten++;
			if(amountEaten >= totalEat) {
				amountEaten = 0;
				Global.player.addFood(fill);
				Global.player.addSaturation(saturation);
				ToolBar.items.get(type).remove(this);
			}
		}
	}
	
	@Override
	public void stoppedUse() {
		amountEaten = 0;
	}
}
