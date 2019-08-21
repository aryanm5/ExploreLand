package items;

public class Coal extends Item {
	public Coal(double x, double y) {
		super(x, y);
		type = "items.Coal";
		imagePath = "resources//items//Coal.png";
		setImage();
	}
}