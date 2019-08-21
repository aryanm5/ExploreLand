package items;

public class StoneShovel extends Item {
	public StoneShovel(double x, double y) {
		super(x, y);
		type = "items.StoneShovel";
		imagePath = "resources//items//StoneShovel.png";
		setImage();
		setDurability(132);
	}
}
