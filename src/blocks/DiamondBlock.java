package blocks;

public class DiamondBlock extends Block {

	public DiamondBlock(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.DiamondBlock";
		imagePath = "resources//blocks//DiamondBlock.png";
		setImage();
		String[] tools = {"items.IronPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 25.0);
		breakSpeeds.put("items.WoodenPickaxe", 12.5);
		breakSpeeds.put("items.StonePickaxe", 6.25);
		breakSpeeds.put("items.IronPickaxe", 1.25);
		breakSpeeds.put("items.DiamondPickaxe", 0.95);
		breakSpeeds.put("items.GoldenPickaxe", 2.1);
	}
	
}
