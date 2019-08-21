package items;

public class StonePickaxe extends Item {
	public StonePickaxe(double x, double y) {
		super(x, y);
		type = "items.StonePickaxe";
		imagePath = "resources//items//StonePickaxe.png";
		setImage();
		setDurability(132);
	}
}
