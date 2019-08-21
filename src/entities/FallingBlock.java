package entities;

import org.newdawn.slick.Input;

import general.Global;

public class FallingBlock extends Entity {
	public String type;
	
	public FallingBlock(double x, double y, String type) {
		super(x, y);
		this.type = type;
		width = 30;
		height = 30;
		imagePath = "resources//blocks//" + type.split("\\.")[1] + ".png";
		setImage();
	}
	
	public void update(Input input) {
		super.update(input);
		if(xVelocity == 0 && yVelocity == 0) {
			if(Global.getBlock((int) x, (int) y+1).type.equals("blocks.Air") || Global.getBlock((int) x, (int) y+1).type.equals("blocks.Water")) {
				Global.blocks.get((int) x).set((int) y+1, Global.stringToBlock(type, (int) x, (int) y+1));
			} else {
				Global.items.add(Global.blockToItem(type, x + Math.random()*5/10.0, y - Math.random()*5/10.0));
			}
			Global.entities.remove(this);
		}
	}
}
