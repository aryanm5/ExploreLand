package blocks;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import general.Crafting;
import general.Global;
import general.Smelting;
import general.ToolBar;

public class Air extends Block {

	public Air(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		falls = false;
		type = "blocks.Air";
		imagePath = "resources//blocks//Air.png";
		blocksPlayer = false;
		light = true;
		transparent = true;
		transparency = 0f;
		setImage();
	}
	public Air(int xCoord, int yCoord, float transparency) {
		this(xCoord, yCoord);
		setTransparency(transparency);
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
				setTransparency(0.7f);
				light = false;
			} else {
				setTransparency(0f); //low transparency = brighter air
				light = true;
			}
		} else {
			light = false;
		}
		
		if(brightness*1.5f > transparency) {
			setTransparency(1-brightness*1.5f);
		} else {
			setTransparency(0.7f);
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
	
	@Override
	public void breakBlock() {}
	
	public void setTransparency(float newTransparency) {
		if(transparency != newTransparency) {
			transparency = newTransparency;
			image.setImageColor(brightness, brightness, brightness, transparency);
		}
	}
}
