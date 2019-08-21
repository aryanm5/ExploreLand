package general;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.HashMap;

import javax.swing.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class GameLoop extends BasicGame {

	public int fps;
	
	public GameLoop() {
		super("ExploreLand -- AryanM");
		// TODO Auto-generated constructor stub
	}
	

	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if(Global.hour >= 5  && Global.hour <= 17) {
			g.setColor(Global.DAY_COLOR); //day 209, 236, 253 | night 56, 40, 92
			Global.dayPart = "Day";
		} else if(Global.hour == 18 || Global.hour == 19) {
			g.setColor(Global.DUSK_COLOR);
			Global.dayPart = "Dusk";
		} else {
			g.setColor(Global.NIGHT_COLOR);
			Global.dayPart = "Night";
		}
		g.fillRect(Global.app.getWidth()/2 - Global.settings.get("RenderWidth")*30, Global.app.getHeight()/2 - Global.settings.get("RenderHeight")*30, Global.settings.get("RenderWidth")*60, Global.settings.get("RenderHeight")*60);
		int rw = Global.settings.get("RenderWidth");
		int rh = Global.settings.get("RenderHeight");
		//blocks
		for(int x = (int) Global.player.x - rw; x < (int) Global.player.x + rw + 1; ++x) {
			if(x < 0 || x >= Global.world.width) { continue; }
			for(int y = (int) Global.player.y - rh; y < (int) Global.player.y + rh + 1; ++y) {
				if(y < 0 || y >= Global.world.height) { continue; }
				Global.blocks.get(x).get(y).xPixel = x*30 - Coord.toPixels(Global.player.x - 30);
				Global.blocks.get(x).get(y).yPixel = (29-y)*30 - (29-Coord.toPixels(Global.player.y - 14));
				try {
				Global.blocks.get(x).get(y).image.draw(Global.blocks.get(x).get(y).xPixel, Global.blocks.get(x).get(y).yPixel, Global.blocks.get(x).get(y).zoom);
				} catch(Exception ex) {
					//System.out.println("Error drawing block (" + x + ", " + y + ") in GameLoop render()");
				}
				
				if(Global.blocks.get(x).get(y).isHovered == true && Math.abs(x-Global.player.x) <= 5 && Math.abs(y-Global.player.y) <= 5) {
					g.setColor(Color.red);
					g.drawRect(Global.blocks.get(x).get(y).xPixel + 1, Global.blocks.get(x).get(y).yPixel + 1, 28, 28);
					g.setColor(new Color(230, 230, 230, 0.5f));
					g.fillRect(Global.getBlock(x, y).xPixel, Global.getBlock(x, y).yPixel + 30 - (float) (Global.getBlock(x, y).amountBroken/Global.getBlock(x, y).breakTime*30), 30, (float) (Global.getBlock(x, y).amountBroken/Global.getBlock(x, y).breakTime*30));
				}
				
				 //Stev Block: g.drawRect(Global.getBlock((int) (Global.player.x + 0.5), (int) (Global.player.y +0.5)).xPixel + 1, Global.getBlock((int) (Global.player.x + 0.5), (int) (Global.player.y+0.5)).yPixel + 1, 28, 28);
			}
		}
		
		//time
		Global.time++;
		if(Global.time >= Global.DAY_LENGTH) { Global.time = 0; }
		
		//entities
		for(int i = 0; i < Global.entities.size(); ++i) {
			Global.entities.get(i).draw(g);
		}
		//player
		Global.player.draw(g);
		
		//items
		for(int i = 0; i < Global.items.size(); ++i) {
			Global.items.get(i).draw(g);
		}
		//text
		g.setColor(Color.red);
		g.drawString("FPS: " + fps, 10, 10);
		g.drawString("x: " + String.format("%.2f", Global.player.x) + ", y: " + String.format("%.2f", Global.player.y), 10,30);
		
		double hour = (((double) (Global.time)/Global.DAY_LENGTH) * 24);
		int min = (int) ((hour - (int) (hour))*60);
		Global.hour = (int) hour;
		
		g.setColor((Global.dayPart.equals("Day")) ? Color.black : Color.white);
		g.drawString("T I M E: " + (int) ((hour <= 12) ? hour+1 : hour-11) + ":" + ((min < 10) ? "0" + min : min), 10, 60);
		
		//toolbar
		Global.toolbar.draw(g);
		
		//Crafting
		Crafting.drawButtons(g);
		Smelting.drawButtons(g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		gc.setIcons(new String[] { "resources//general//stevicon16.png", "resources//general//stevicon32.png" });
		Global.init();
		Global.world = new World();
		Global.app.setTitle("ExploreLand -- GENERATING WORLD...");
		System.out.println("Generating World...");
		Global.world.generateWorld();
		
		//toolbar
		Global.toolbar = new ToolBar();
		
		Global.crafter = new Crafting();
		Crafting.addRecipes();
		
		Global.smelter = new Smelting();
		Smelting.addRecipes();
		
		if(Global.settings.get("Crosshair") == 1) {
			gc.setMouseCursor(new Image("resources//general//crosshair.png"), 16, 16);
		}
		Global.app.setTitle("ExploreLand -- AryanM");
		System.out.println("Done!");
	}
	

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		//TODO Auto-generated method stub
		if(Global.settings.get("InfiniteWorld") == 1 && (int) Global.player.x + 34 >= Global.world.width) {
			Global.world.generateColumn((int) Global.player.x + 34);
		}
		
		Input input = gc.getInput();
		Global.toolbar.update(input);
		Global.player.update(input);
		for(int x = (int) Global.player.x - Global.settings.get("RenderWidth") - 4; x < (int) Global.player.x + Global.settings.get("RenderWidth") + 4; ++x) {
			if(x < 0 || x >= Global.world.width) { continue; }
			for(int y = (int) Global.player.y - Global.settings.get("RenderHeight") - 4; y < (int) Global.player.y + Global.settings.get("RenderHeight") + 4; ++y) {
				if(y < 0 || y >= Global.world.height) { continue; }
				Global.blocks.get(x).get(y).update(input);
			}
		}
		
		Crafting.update(input);
		Smelting.update(input);
		
		for(int i = 0; i < Global.items.size(); ++i) {
			Global.items.get(i).update(input);
		}
		
		for(int i = 0; i < Global.entities.size(); ++i) {
			Global.entities.get(i).update(input);
		}
		
		fps = gc.getFPS();
	}
	
	public static void startMenu() {
		JFrame frame = new JFrame("ExploreLand Start Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		Container p = frame.getContentPane();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		JPanel pRenderWidth = new JPanel();
		JPanel pRenderHeight = new JPanel();
		JPanel pBrightRadius = new JPanel();
		JPanel pWorldHeight = new JPanel();
		JPanel pInitialWorldWidth = new JPanel();
		JPanel pPlayerName = new JPanel();
		JPanel pPlayerImagePath = new JPanel();
		JPanel pCrosshair = new JPanel();
		JPanel pInfiniteWorld = new JPanel();
		
		HashMap<String, JComponent> fields = new HashMap<String, JComponent>();
		
		fields.put("renderWidth", new JTextField("30", 2));
		fields.put("renderHeight", new JTextField("15", 2));
		fields.put("brightRadius", new JTextField("3", 2));
		fields.put("worldHeight", new JTextField("180", 4));
		fields.put("initialWorldWidth", new JTextField("70", 4));
		fields.put("playerName", new JTextField("Stev", 10));
		fields.put("playerImagePath", new JTextField("resources/entities/stev.png", 30));
		fields.put("crosshair", new JCheckBox("", true));
		fields.put("infiniteWorld", new JCheckBox("", true));
		
		JButton generateButton = new JButton("Generate World!");
		
		pRenderWidth.add(new JLabel("Render Width* (blocks): "));
		pRenderWidth.add(fields.get("renderWidth"));
		pRenderHeight.add(new JLabel("Render Height* (blocks): "));
		pRenderHeight.add(fields.get("renderHeight"));
		pBrightRadius.add(new JLabel("Brightness Radius* (blocks): "));
		pBrightRadius.add(fields.get("brightRadius"));
		pWorldHeight.add(new JLabel("World Height* (blocks): "));
		pWorldHeight.add(fields.get("worldHeight"));
		pInitialWorldWidth.add(new JLabel("Initial World Width: "));
		pInitialWorldWidth.add(fields.get("initialWorldWidth"));
		pPlayerName.add(new JLabel("Player Name: "));
		pPlayerName.add(fields.get("playerName"));
		pPlayerImagePath.add(new JLabel("Player Skin: "));
		pPlayerImagePath.add(fields.get("playerImagePath"));
		pCrosshair.add(new JLabel("Crosshair Mouse: "));
		pCrosshair.add(fields.get("crosshair"));
		pCrosshair.add(new JLabel("Infinite World: "));
		pCrosshair.add(fields.get("infiniteWorld"));
		
		p.add(new JLabel("ExploreLand -- AryanM | START MENU"));
		p.add(new JLabel(" "));
		p.add(new JLabel("Default settings recommended."));
		p.add(new JLabel("* Lowering/Disabling reduces lag"));
		p.add(new JLabel(" "));
		
		p.add(pRenderWidth);
		p.add(pRenderHeight);
		p.add(pBrightRadius);
		p.add(pWorldHeight);
		p.add(pInitialWorldWidth);
		p.add(pPlayerName);
		p.add(pPlayerImagePath);
		p.add(pCrosshair);
		p.add(pInfiniteWorld);
		
		//frame.getContentPane().add(BorderLayout.CENTER, p);
		generateButton.addActionListener(new GenerateButtonListener(frame, fields));
		frame.getContentPane().add(BorderLayout.SOUTH, generateButton);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	@Override
	public void mouseReleased(int button, int x, int y) {
		ToolBar.mouseReleased(button, x, y);
	}
	public static void main(String[] args) {
		try {
			ScalableGame s = new ScalableGame(new GameLoop(), 1800, 900, true);
			Global.app = new AppGameContainer(s);
			Global.app.setVerbose(false);
			Global.app.setDisplayMode(1800, 900, false);
			Global.app.setTargetFrameRate(100);
			startMenu();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
