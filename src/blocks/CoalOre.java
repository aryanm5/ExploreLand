package blocks;

public class CoalOre extends Block {

	public CoalOre(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.CoalOre";
		imagePath = "resources//blocks//CoalOre.png";
		setImage();
		String[] tools = {"items.WoodenPickaxe", "items.StonePickaxe", "items.IronPickaxe", "items.GoldenPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		drop = "items.Coal";
		breakSpeeds.replace("Empty", 15.0);
		breakSpeeds.put("items.WoodenPickaxe", 2.25);
		breakSpeeds.put("items.StonePickaxe", 1.15);
		breakSpeeds.put("items.IronPickaxe", 0.75);
		breakSpeeds.put("items.DiamondPickaxe", 0.6);
		breakSpeeds.put("items.GoldenPickaxe", 0.4);
	}
	
}
