package blocks;

public class Dirt extends Block {

	public Dirt(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Dirt";
		imagePath = "resources//blocks//Dirt.png";
		setImage();
		breakSpeeds.replace("Empty", 0.75);
		breakSpeeds.put("items.WoodenShovel", 0.4);
		breakSpeeds.put("items.StoneShovel", 0.2);
		breakSpeeds.put("items.IronShovel", 0.15);
		breakSpeeds.put("items.DiamondShovel", 0.1);
		breakSpeeds.put("items.GoldenShovel", 0.1);
	}
	
}
