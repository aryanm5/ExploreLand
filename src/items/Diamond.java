package items;

public class Diamond extends Item {
	public Diamond(double x, double y) {
		super(x, y);
		type = "items.Diamond";
		imagePath = "resources//items//Diamond.png";
		setImage();
	}
}