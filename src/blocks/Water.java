package blocks;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import general.Crafting;
import general.Global;
import general.Smelting;
import general.ToolBar;

public class Water extends Block {
	public boolean canFlow = false;
	
	public Water(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Water";
		imagePath = "resources//blocks//Water.png";
		blocksPlayer = false;
		drownsMobs = true;
		setImage();
		dropsWithTool.remove(0);
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		if(Global.time % 100 == 0 && canFlow) {
			if(y > 0 && !Global.getBlock(x, y-1).type.equals("blocks.Air") && !Global.getBlock(x, y-1).type.equals("blocks.Water")) {
				if(x > 0 && Global.getBlock(x-1, y).type.equals("blocks.Air")) {
					Global.blocks.get(x-1).set(y, new Water(x-1, y));
				}
				if(x < Global.world.width-1 && Global.getBlock(x+1, y).type.equals("blocks.Air")) {
					Global.blocks.get(x+1).set(y, new Water(x+1, y));
				}
			} else if(y > 0 && !Global.getBlock(x, y-1).type.equals("blocks.Water")) {
				Global.blocks.get(x).set(y-1, new Water(x, y-1));
			}
		}
		
		if(!canFlow) {
			canFlow = true;
		}
	}
	@Override
	public void isRightClicked() throws Exception {
		if(isHovered && !ToolBar.selectedItem.startsWith("Empty") && !ToolBar.selectedItem.startsWith("items")) {
			Block o = Global.stringToBlock(ToolBar.selectedItem, x, y);
			Global.blocks.get(x).set(y, o);
			ToolBar.removeItem(ToolBar.selectedItem, 1);
			Global.blocks.get(x).get(y).image.setFilter(Image.FILTER_NEAREST);
			
			Crafting.isOpen = false;
			Smelting.isOpen = false;
		}
	}
	@Override
	public void isBeingMined() {}
}
