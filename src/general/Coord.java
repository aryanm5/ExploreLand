package general;

public abstract class Coord {
	public static int toPixels(double coord) {
		int factor = /*(int) (screenWidth/30)*/30;
		return (int) (coord*factor);
	}
}
