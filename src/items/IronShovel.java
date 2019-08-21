package items;

public class IronShovel extends Item {
	public IronShovel(double x, double y) {
		super(x, y);
		type = "items.IronShovel";
		imagePath = "resources//items//ironShovel.png";
		setImage();
		setDurability(251);
	}
}
