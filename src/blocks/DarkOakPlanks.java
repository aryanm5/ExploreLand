package blocks;


public class DarkOakPlanks extends Block {

	public DarkOakPlanks(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.DarkOakPlanks";
		imagePath = "resources//blocks//DarkOakPlanks.png";
		setImage();
		breakSpeeds.replace("Empty", 3.0);
		breakSpeeds.put("items.WoodenAxe", 1.5);
		breakSpeeds.put("items.StoneAxe", 0.75);
		breakSpeeds.put("items.IronAxe", 0.5);
		breakSpeeds.put("items.GoldenAxe", 0.25);
		breakSpeeds.put("items.DiamondAxe", 0.4);
	}
	
}
