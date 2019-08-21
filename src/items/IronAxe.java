package items;

public class IronAxe extends Item {
	public IronAxe(double x, double y) {
		super(x, y);
		type = "items.IronAxe";
		imagePath = "resources//items//IronAxe.png";
		setImage();
		setDurability(251);
	}
}
