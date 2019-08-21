package items;

public class GoldenShovel extends Item {
	public GoldenShovel(double x, double y) {
		super(x, y);
		type = "items.GoldenShovel";
		imagePath = "resources//items//GoldenShovel.png";
		setImage();
		setDurability(33);
	}
}
