package blocks;


public class OakPlanks extends Block {

	public OakPlanks(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.OakPlanks";
		imagePath = "resources//blocks//OakPlanks.png";
		setImage();
		breakSpeeds.replace("Empty", 3.0);
		breakSpeeds.put("items.WoodenAxe", 1.5);
		breakSpeeds.put("items.StoneAxe", 0.75);
		breakSpeeds.put("items.IronAxe", 0.5);
		breakSpeeds.put("items.GoldenAxe", 0.25);
		breakSpeeds.put("items.DiamondAxe", 0.4);
	}
	
}
