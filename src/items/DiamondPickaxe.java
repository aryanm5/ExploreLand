package items;

public class DiamondPickaxe extends Item {
	public DiamondPickaxe(double x, double y) {
		super(x, y);
		type = "items.DiamondPickaxe";
		imagePath = "resources//items//DiamondPickaxe.png";
		setImage();
		setDurability(1562);
	}
}
