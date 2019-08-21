package blocks;


public class Sandstone extends Block {

	public Sandstone(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Sandstone";
		imagePath = "resources//blocks//Sandstone.png";
		setImage();
		String[] tools = {"items.WoodenPickaxe", "items.StonePickaxe", "items.IronPickaxe", "items.GoldenPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 4.0);
		breakSpeeds.put("items.WoodenPickaxe", 0.65);
		breakSpeeds.put("items.StonePickaxe", 0.35);//0.2
		breakSpeeds.put("items.IronPickaxe", 0.2);
		breakSpeeds.put("items.DiamondPickaxe", 0.2);
		breakSpeeds.put("items.GoldenPickaxe", 0.1);
	}
	
}
