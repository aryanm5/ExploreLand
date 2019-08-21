package blocks;

public class DiamondOre extends Block {

	public DiamondOre(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.DiamondOre";
		imagePath = "resources//blocks//DiamondOre.png";
		setImage();
		drop = "items.Diamond";
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
