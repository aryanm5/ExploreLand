package items;

public class GoldenPickaxe extends Item {
	public GoldenPickaxe(double x, double y) {
		super(x, y);
		type = "items.GoldenPickaxe";
		imagePath = "resources//items//GoldenPickaxe.png";
		setImage();
		setDurability(33);
	}
}
