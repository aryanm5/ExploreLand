package blocks;

public class IronBlock extends Block {

	public IronBlock(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.IronBlock";
		imagePath = "resources//blocks//IronBlock.png";
		setImage();
		String[] tools = {"items.StonePickaxe", "items.IronPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 25.0);
		breakSpeeds.put("items.WoodenPickaxe", 12.5);
		breakSpeeds.put("items.StonePickaxe", 1.9);
		breakSpeeds.put("items.IronPickaxe", 1.25);
		breakSpeeds.put("items.DiamondPickaxe", 0.95);
		breakSpeeds.put("items.GoldenPickaxe", 2.1);
	}
	
}
