package blocks;

import general.Global;

public class BirchLog extends Block {

	public BirchLog(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.BirchLog";
		imagePath = "resources//blocks//BirchLog.png";
		setImage();
		breakSpeeds.replace("Empty", 3.0);
		breakSpeeds.put("items.WoodenAxe", 1.5);
		breakSpeeds.put("items.StoneAxe", 0.75);
		breakSpeeds.put("items.IronAxe", 0.5);
		breakSpeeds.put("items.GoldenAxe", 0.25);
		breakSpeeds.put("items.DiamondAxe", 0.4);
	}
	
	public BirchLog(int xCoord, int yCoord, boolean blockPlayer) {
		this(xCoord, yCoord);
		blocksPlayer = blockPlayer;
		transparent = blocksPlayer;
	}
	
	@Override
	public void isRightClicked() throws Exception {
		if(blocksPlayer) {
			Global.replaceWithCraftingTable(x, y, type);
		}
	}
}
