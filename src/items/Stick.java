package items;

public class Stick extends Item {
	public Stick(double x, double y) {
		super(x, y);
		type = "items.Stick";
		imagePath = "resources//items//Stick.png";
		setImage();
	}
}
