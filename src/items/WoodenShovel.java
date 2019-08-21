package items;

public class WoodenShovel extends Item {
	public WoodenShovel(double x, double y) {
		super(x, y);
		type = "items.WoodenShovel";
		imagePath = "resources//items//WoodenShovel.png";
		setImage();
		setDurability(60);
	}
}
