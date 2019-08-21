package items;

public class DiamondShovel extends Item {
	public DiamondShovel(double x, double y) {
		super(x, y);
		type = "items.DiamondShovel";
		imagePath = "resources//items//DiamondShovel.png";
		setImage();
		setDurability(1562);
	}
}
