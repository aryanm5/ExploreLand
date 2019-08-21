package blocks;

public class Torch extends Block {

	public Torch(int xCoord, int yCoord) {
		super(xCoord, yCoord);
		type = "blocks.Torch";
		imagePath = "resources//blocks//Torch.png";
		light = true;
		transparent = true;
		blocksPlayer = false;
		setImage();
		breakSpeeds.replace("Empty", 0.01);
	}
	
}
