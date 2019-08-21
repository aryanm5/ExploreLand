package blocks;

public class IronOre extends Block {

	public IronOre(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.IronOre";
		imagePath = "resources//blocks//IronOre.png";
		setImage();
		String[] tools = {"items.StonePickaxe", "items.IronPickaxe", "items.GoldenPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 15.0);
		breakSpeeds.put("items.WoodenPickaxe", 7.5);
		breakSpeeds.put("items.StonePickaxe", 1.15);
		breakSpeeds.put("items.IronPickaxe", 0.75);
		breakSpeeds.put("items.DiamondPickaxe", 0.6);
		breakSpeeds.put("items.GoldenPickaxe", 1.25);
	}
	
}
