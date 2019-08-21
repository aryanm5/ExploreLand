package items;

public class WoodenAxe extends Item {
	public WoodenAxe(double x, double y) {
		super(x, y);
		type = "items.WoodenAxe";
		imagePath = "resources//items//WoodenAxe.png";
		setImage();
		setDurability(60);
	}
}
