package items;

public class WoodenPickaxe extends Item {
	public WoodenPickaxe(double x, double y) {
		super(x, y);
		type = "items.WoodenPickaxe";
		imagePath = "resources//items//WoodenPickaxe.png";
		setImage();
		setDurability(60);
	}
}
