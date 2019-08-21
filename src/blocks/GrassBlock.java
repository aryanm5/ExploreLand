package blocks;

import org.newdawn.slick.Input;

import general.Global;

public class GrassBlock extends Block {

	public GrassBlock(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.GrassBlock";
		imagePath = "resources//blocks//GrassBlock.png";
		setImage();
		drop = "blocks.Dirt";
		breakSpeeds.replace("Empty", 0.9);
		breakSpeeds.put("items.WoodenShovel", 0.45);
		breakSpeeds.put("items.StoneShovel", 0.25);
		breakSpeeds.put("items.IronShovel", 0.15);
		breakSpeeds.put("items.DiamondShovel", 0.15);
		breakSpeeds.put("items.GoldenShovel", 0.1);
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		if(Global.time % (int) (Math.random()*4999 + 1) == 0 && y < Global.world.height-1 && Global.getBlock(x, y+1).blocksPlayer) {
			Global.blocks.get(x).set(y, new Dirt(x, y));
		}
	}
}
