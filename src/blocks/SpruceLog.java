package blocks;

import general.Global;

public class SpruceLog extends Block {

	public SpruceLog(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.SpruceLog";
		imagePath = "resources//blocks//SpruceLog.png";
		setImage();
		breakSpeeds.replace("Empty", 3.0);
		breakSpeeds.put("items.WoodenAxe", 1.5);
		breakSpeeds.put("items.StoneAxe", 0.75);
		breakSpeeds.put("items.IronAxe", 0.5);
		breakSpeeds.put("items.GoldenAxe", 0.25);
		breakSpeeds.put("items.DiamondAxe", 0.4);
	}
	
	public SpruceLog(int xCoord, int yCoord, boolean blockPlayer) {
		this(xCoord, yCoord);
		blocksPlayer = blockPlayer;
	}
	
	@Override
	public void isRightClicked() throws Exception {
		if(blocksPlayer) {
			Global.replaceWithCraftingTable(x, y, type);
		}
	}
}
