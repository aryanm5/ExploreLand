package blocks;

public class GoldOre extends Block {

	public GoldOre(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.GoldOre";
		imagePath = "resources//blocks//GoldOre.png";
		setImage();
		String[] tools = {"items.IronPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 12.0);
		breakSpeeds.put("items.WoodenPickaxe", 7.5);
		breakSpeeds.put("items.StonePickaxe", 3.75);
		breakSpeeds.put("items.IronPickaxe", 0.75);
		breakSpeeds.put("items.DiamondPickaxe", 0.6);
		breakSpeeds.put("items.GoldenPickaxe", 1.25);
	}
	
}
