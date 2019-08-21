package blocks;

import org.newdawn.slick.Input;

import general.Global;

public class SquashyBlock extends Block {
	public boolean jiggling = false;
	public long startJiggleTime = 0;

	public SquashyBlock(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.SquashyBlock";
		imagePath = "resources//blocks//SquashyBlock.png";
		setImage();
		drop = "blocks.Cobblestone";
		String[] tools = {"items.WoodenAxe", "items.StoneAxe", "items.IronAxe", "items.GoldenAxe", "items.DiamondAxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 7.0);
		breakSpeeds.put("items.WoodenAxe", 0.5);
		breakSpeeds.put("items.StoneAxe", 1.0);
		breakSpeeds.put("items.IronAxe", 2.5);
		breakSpeeds.put("items.GoldenAxe", 4.0);
		breakSpeeds.put("items.DiamondAxe", 5.0);
		Global.player.y -= 1;
	}
	@Override
	public void update(Input input) {
		super.update(input);
		if(jiggling) {
			Global.player.x += Math.random()*1;
			Global.player.x -= Math.random()*1;
			if(System.currentTimeMillis() >= startJiggleTime + 15000) {
				jiggling = false;
			}
		}
	}
	
	@Override
	public void breakBlock() {
		super.breakBlock();
		Global.player.y += 100;
	}
	@Override
	public void isRightClicked() {
		startJiggleTime = System.currentTimeMillis();
		jiggling = true;
	}
	
}
