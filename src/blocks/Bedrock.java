package blocks;

public class Bedrock extends Block {

	public Bedrock(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Bedrock";
		imagePath = "resources//blocks//Bedrock.png";
		setImage();
		
		breakSpeeds.replace("Empty", 25.0);
	}
	
	@Override
	public void isBeingMined() {
		amountBroken += 10/breakSpeeds.get("Empty");
		if(amountBroken > 900.0) {
			breakSpeeds.replace("Empty", -5.0);
		}
		if(amountBroken < 50.0) {
			breakSpeeds.replace("Empty", 25.0);
		}
	}
}
