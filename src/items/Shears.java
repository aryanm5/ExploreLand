package items;

public class Shears extends Item {
	public Shears(double x, double y) {
		super(x, y);
		type = "items.Shears";
		imagePath = "resources//items//Shears.png";
		setImage();
	}
}
