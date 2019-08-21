package items;

public class GoldIngot extends Item {
	public GoldIngot(double x, double y) {
		super(x, y);
		type = "items.GoldIngot";
		imagePath = "resources//items//GoldIngot.png";
		setImage();
	}
}