package blocks;

import general.Crafting;

public class CraftingTable extends Block {

	public CraftingTable(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.CraftingTable";
		imagePath = "resources//blocks//CraftingTable.png";
		setImage();
		breakSpeeds.replace("Empty", 3.75);
		breakSpeeds.put("items.WoodenAxe", 1.9);
		breakSpeeds.put("items.StoneAxe", 0.95);
		breakSpeeds.put("items.IronAxe", 0.65);
		breakSpeeds.put("items.DiamondAxe", 0.5);
		breakSpeeds.put("items.GoldenAxe", 0.35);
	}
	
	public void isRightClicked() {
		if(Crafting.isOpen == false) {
		Crafting crafter = new Crafting();
		crafter.openButtons();
		} else {
			Crafting.isOpen = false;
		}
	}
	
}
