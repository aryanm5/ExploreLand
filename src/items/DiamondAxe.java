package items;

public class DiamondAxe extends Item {
	public DiamondAxe(double x, double y) {
		super(x, y);
		type = "items.DiamondAxe";
		imagePath = "resources//items//DiamondAxe.png";
		setImage();
		setDurability(1562);
	}
}
