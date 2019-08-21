package blocks;


public class Cobblestone extends Block {

	public Cobblestone(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Cobblestone";
		imagePath = "resources//blocks//Cobblestone.png";
		setImage();
		String[] tools = {"items.WoodenPickaxe", "items.StonePickaxe", "items.IronPickaxe", "items.GoldenPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 10.0);
		breakSpeeds.put("items.WoodenPickaxe", 1.5);
		breakSpeeds.put("items.StonePickaxe", 0.75);
		breakSpeeds.put("items.IronPickaxe", 0.5);
		breakSpeeds.put("items.GoldenPickaxe", 0.25);
		breakSpeeds.put("items.DiamondPickaxe", 0.4);
	}
	
}
