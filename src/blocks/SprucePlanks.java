package blocks;

public class SprucePlanks extends Block {

	public SprucePlanks(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.SprucePlanks";
		imagePath = "resources//blocks//SprucePlanks.png";
		setImage();
		breakSpeeds.replace("Empty", 3.0);
		breakSpeeds.put("items.WoodenAxe", 1.5);
		breakSpeeds.put("items.StoneAxe", 0.75);
		breakSpeeds.put("items.IronAxe", 0.5);
		breakSpeeds.put("items.GoldenAxe", 0.25);
		breakSpeeds.put("items.DiamondAxe", 0.4);
	}
	
}
