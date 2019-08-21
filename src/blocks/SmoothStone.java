package blocks;

public class SmoothStone extends Block {

	public SmoothStone(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.SmoothStone";
		imagePath = "resources//blocks//SmoothStone.png";
		setImage();
		String[] tools = {"items.WoodenPickaxe", "items.StonePickaxe", "items.IronPickaxe", "items.GoldenPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 7.5);
		breakSpeeds.put("items.WoodenPickaxe", 1.15);
		breakSpeeds.put("items.StonePickaxe", 0.6);
		breakSpeeds.put("items.IronPickaxe", 0.4);
		breakSpeeds.put("items.GoldenPickaxe", 0.2);
		breakSpeeds.put("items.DiamondPickaxe", 0.3);
	}
	
}
