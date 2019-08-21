package items;

import org.newdawn.slick.Input;

import general.Global;

//trying to put Blocks into toolbar.items
//maybe put all Block() classes into items package...
//or use this and have "type" String of all classes be "blocks.Dirt" or "items.Coal" instead of only "Dirt" or "Coal"
//and replace wherever it says "blocks." + type or for items
public class BlockItem extends Item {
	public BlockItem(String blockName, double x, double y) {
		super(x, y);
		type = blockName;
		imagePath = "resources//blocks//" + blockName.split("\\.")[1] + ".png";
		setImage();
	}
}
