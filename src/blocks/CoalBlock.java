package blocks;

public class CoalBlock extends Block {

	public CoalBlock(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.CoalBlock";
		imagePath = "resources//blocks//CoalBlock.png";
		setImage();
		String[] tools = {"items.WoodenPickaxe", "items.StonePickaxe", "items.IronPickaxe", "items.GoldenPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 25.0);
		breakSpeeds.put("items.WoodenPickaxe", 3.75);
		breakSpeeds.put("items.StonePickaxe", 1.9);
		breakSpeeds.put("items.IronPickaxe", 1.25);
		breakSpeeds.put("items.DiamondPickaxe", 0.95);
		breakSpeeds.put("items.GoldenPickaxe", 0.65);
	}
	
}
