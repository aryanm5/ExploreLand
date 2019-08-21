package blocks;

import org.newdawn.slick.Input;

import entities.FallingBlock;
import general.Global;

public class Sand extends Block {

	public Sand(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Sand";
		falls = true;
		isSmeltable = true;
		smeltOutput = "blocks.Glass";
		imagePath = "resources//blocks//Sand.png";
		setImage();
		breakSpeeds.replace("Empty", 0.75);
		breakSpeeds.put("items.WoodenShovel", 0.4);
		breakSpeeds.put("items.StoneShovel", 0.2);
		breakSpeeds.put("items.IronShovel", 0.15);
		breakSpeeds.put("items.DiamondShovel", 0.1);
		breakSpeeds.put("items.GoldenShovel", 0.1);
	}
	
}
