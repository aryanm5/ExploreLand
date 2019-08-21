package items;

public class Apple extends Food {
	public Apple(double x, double y) {
		super(x, y);
		type = "items.Apple";
		imagePath = "resources//items//Apple.png";
		setImage();
		fill = 4;
		saturation = 2.4;
	}
}