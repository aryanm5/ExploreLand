package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import general.Coord;
import general.Global;

public class Entity {
	public Image image;
	public String imagePath;
	public double x; //block's pixel location
	public double y;
	public int xPixel;
	public int yPixel;
	public double yVelocity = 0;
	public double xVelocity = 0;
	public int width;
	public int height;
	
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setImage() {
		try {
			image = new Image(imagePath);
			image.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		image.draw(xPixel, yPixel, width, height);
	//	g.setColor(Color.red);
	//	g.fillRect(Global.getBlock((int) x, (int) y).xPixel, Global.getBlock((int) x, (int) (y+1)).yPixel, 30, 30);
	}
	
	public void update(Input input) {
		xPixel = (int) (x*30 - Coord.toPixels(Global.player.x - 30));
		yPixel = (int) ((29-y)*30 - (29-Coord.toPixels(Global.player.y - 14)));
		
		if((x < 0 || (int) x >= Global.world.width || y < 0 || (int) y >= Global.world.height) || y > 0 && y + yVelocity < Global.world.height - 1 && !Global.getBlock((int) (x), (int) (y)).blocksPlayer) {
			if(x >= 0 && x < Global.world.width && y >= 0 && y < Global.world.height && !Global.getBlock((int) (x), (int) (y)).type.equals("blocks.Water")) {
				yVelocity += 0.003;
			} else {
				if(yVelocity < 0.05) {
					yVelocity += 0.002;
				} else {
					yVelocity -= 0.002;
				}
				if(xVelocity > 0.05) {
					xVelocity -= 0.003;
					if(xVelocity < 0) { xVelocity = 0; }
				} else if(xVelocity < -0.05) {
					xVelocity += 0.003;
					if(xVelocity > 0) { xVelocity = 0; }
				}
			}
		} else {
			yVelocity = 0;
			if(xVelocity > 0) {
				if(x >= 0 && x < Global.world.width && y >= 0 && y < Global.world.height && !Global.getBlock((int) (x), (int) (y)).type.equals("blocks.Water")) {
					xVelocity -= 0.005;
				} else {
					xVelocity -= 0.05;
				}
				if(xVelocity < 0) {
					xVelocity = 0;
				}
			} else {
				if(x >= 0 && x < Global.world.width && y >= 0 && y < Global.world.height && !Global.getBlock((int) (x), (int) (y)).type.equals("blocks.Water")) {
					xVelocity += 0.005;
				} else {
					xVelocity += 0.05;
				}
				if(xVelocity > 0) {
					xVelocity = 0;
				}
			}
			if(Global.getBlock((int) (x), (int) (y+1-yVelocity)).blocksPlayer && !Global.getBlock((int) (x), (int) (y+1)).blocksPlayer) {
				yVelocity = 0.1;
			}
		}
		
		if(x >= 0 && y >= 0 && (int) x < Global.world.width - 1 && (int) y < Global.world.height - 1) {
			if(x >= 0 && (int) (x + xVelocity) < Global.world.width && Global.getBlock((int) (x + xVelocity), (int) y + 1).blocksPlayer) {
				xVelocity = 0;
			}
		}
		
		x += xVelocity;
		y -= yVelocity;
		
		if(y < -5000) {
			Global.entities.remove(this);
		}
	}
}
