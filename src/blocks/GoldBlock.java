package blocks;

public class GoldBlock extends Block {

	public GoldBlock(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.GoldBlock";
		imagePath = "resources//blocks//GoldBlock.png";
		setImage();
		String[] tools = {"items.IronPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 15.0);
		breakSpeeds.put("items.WoodenPickaxe", 7.5);
		breakSpeeds.put("items.StonePickaxe", 3.75);
		breakSpeeds.put("items.IronPickaxe", 0.75);
		breakSpeeds.put("items.DiamondPickaxe", 0.6);
		breakSpeeds.put("items.GoldenPickaxe", 1.25);
	}
	
}
