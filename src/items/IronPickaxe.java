package items;

public class IronPickaxe extends Item {
	
	public IronPickaxe(double x, double y) {
		super(x, y);
		type = "items.IronPickaxe";
		imagePath = "resources//items//IronPickaxe.png";
		setImage();
		setDurability(251);
	}
}
