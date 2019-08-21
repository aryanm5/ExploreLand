package items;

public class GoldenAxe extends Item {
	public GoldenAxe(double x, double y) {
		super(x, y);
		type = "items.GoldenAxe";
		imagePath = "resources//items//GoldenAxe.png";
		setImage();
		setDurability(33);
	}
}
