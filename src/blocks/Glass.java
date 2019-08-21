package blocks;

import org.newdawn.slick.Input;

import general.Global;

public class Glass extends Block {

	public Glass(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Glass";
		transparent = true;
		imagePath = "resources//blocks//Glass.png";
		dropsWithTool.remove(0);
		setImage();
		breakSpeeds.replace("Empty", 0.45);
	}
	@Override
	public void update(Input input) {
		super.update(input);
		if(Global.dayPart.equals("Day")) {
			boolean isCovered = false;
			for(int i = 0; i < Global.world.height - y; ++i) {
				if(!Global.getBlock(x, y+i).transparent) {
					isCovered = true;
					break;
				}
			}
			if(isCovered) {
				light = false;
			} else {
				light = true;
			}
		} else {
			light = false;
		}
	}
}
