package items;

public class IronIngot extends Item {
	public IronIngot(double x, double y) {
		super(x, y);
		type = "items.IronIngot";
		imagePath = "resources//items//IronIngot.png";
		setImage();
	}
}