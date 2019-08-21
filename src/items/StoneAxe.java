package items;

public class StoneAxe extends Item {
	public StoneAxe(double x, double y) {
		super(x, y);
		type = "items.StoneAxe";
		imagePath = "resources//items//StoneAxe.png";
		setImage();
		setDurability(132);
	}
}
