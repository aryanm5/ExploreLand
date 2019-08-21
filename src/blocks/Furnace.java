package blocks;

import general.Smelting;

public class Furnace extends Block {

	public Furnace(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Furnace";
		imagePath = "resources//blocks//Furnace.png";
		setImage();
		String[] tools = {"items.WoodenPickaxe", "items.StonePickaxe", "items.IronPickaxe", "items.GoldenPickaxe", "items.DiamondPickaxe"};
		addDropsWithTools(tools);
		breakSpeeds.replace("Empty", 17.5);
		breakSpeeds.put("items.WoodenPickaxe", 2.65);
		breakSpeeds.put("items.StonePickaxe", 1.35);
		breakSpeeds.put("items.IronPickaxe", 0.9);
		breakSpeeds.put("items.DiamondPickaxe", 0.7);
		breakSpeeds.put("items.GoldenPickaxe", 0.45);
	}
	
	public void isRightClicked() {
		if(Smelting.isOpen == false) {
		Smelting smelter = new Smelting();
		smelter.openButtons();
		} else {
			Smelting.isOpen = false;
		}
	}
}
