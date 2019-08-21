package blocks;

public class Leaves extends Block {

	public Leaves(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Leaves";
		imagePath = "resources//blocks//Leaves.png";
		//light = true;
		transparent = true;
		setImage();
		breakSpeeds.replace("Empty", 1.0);
		String[] tools = {"items.Shears"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 0.35);
		breakSpeeds.put("items.Shears", 0.05);
		breakSpeeds.put("items.WoodenSword", 0.2);
		breakSpeeds.put("items.StoneSword", 0.2);
		breakSpeeds.put("items.IronSword", 0.2);
		breakSpeeds.put("items.DiamondSword", 0.2);
		breakSpeeds.put("items.GoldenSword", 0.2);
	}
	
	public Leaves(int xCoord, int yCoord, boolean blockPlayer) {
		this(xCoord, yCoord);
		blocksPlayer = blockPlayer;
	}
}
