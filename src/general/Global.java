package general;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import blocks.Air;
import blocks.Block;
import blocks.CraftingTable;
import entities.Entity;
import entities.Player;
import items.BlockItem;
import items.Item;

public class Global {
	public static List<List<Block>> blocks = new ArrayList<List<Block>>();
	public static List<Item> items = new ArrayList<Item>();
	public static List<Entity> entities = new ArrayList<Entity>();
	public static Player player;
	public static AppGameContainer app;
	public static World world;
	public static ToolBar toolbar;
	public static Image breakOverlay;
	public static Crafting crafter;
	public static Smelting smelter;
	public static Color background = new Color(209, 236, 253);
	public static final Color DAY_COLOR = new Color(209, 236, 253);
	public static final Color NIGHT_COLOR = new Color(56, 40, 92);
	public static final Color DUSK_COLOR = new Color(138, 113, 154);
	public static final int DAY_LENGTH = 100*300; //100*numOfSeconds
	public static int time = (int) (DAY_LENGTH*(6.2/24));
	public static int hour = 6;
	public static String dayPart = "Day";
	public static Image leg = null; public static Image halfLeg = null; public static Image noLeg = null;
	public static Image heart = null; public static Image halfHeart = null; public static Image noHeart = null;
	public static Image bubble = null;
	public static HashMap<String, Integer> settings = new HashMap<String, Integer>() {{
		put("RenderWidth", 30);//30
		put("RenderHeight", 15);//15
		put("Crosshair", 1);
		put("BrightRadius", 3);
		put("WorldHeight", 180);
		put("InitialWorldWidth", 70);
		put("InfiniteWorld", 1);
		}};
	public static String playerName = "";
	public static String playerImagePath = "";
	public static void init() {
		try {
			leg = new Image("resources//general//FullHunger.png");
			halfLeg = new Image("resources//general//HalfHunger.png");
			noLeg = new Image("resources//general//EmptyHunger.png");
			heart = new Image("resources//general//FullHeart.png");
			halfHeart = new Image("resources//general//HalfHeart.png");
			noHeart = new Image("resources//general//EmptyHeart.png");
			bubble = new Image("resources//general//Bubble.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leg.setFilter(Image.FILTER_NEAREST);
		halfLeg.setFilter(Image.FILTER_NEAREST);
		noLeg.setFilter(Image.FILTER_NEAREST);
		heart.setFilter(Image.FILTER_NEAREST);
		halfHeart.setFilter(Image.FILTER_NEAREST);
		noHeart.setFilter(Image.FILTER_NEAREST);
		bubble.setFilter(Image.FILTER_NEAREST);
	}
	public static Block getBlock(int blockX, int blockY) {
		return blocks.get(blockX).get(blockY);
	}
	
	public static void replaceWithCraftingTable(int x, int y, String type) {
		if(Global.getBlock(x, y+1).type == type && Global.getBlock(x, y+1).blocksPlayer && 
				Global.getBlock(x-1, y).type == type && Global.getBlock(x-1, y).blocksPlayer &&
				Global.getBlock(x-1, y+1).type == type && Global.getBlock(x-1, y+1).blocksPlayer) {
			Global.blocks.get(x).set(y+1, new Air(x, y+1));
			Global.blocks.get(x-1).set(y, new Air(x-1, y));
			Global.blocks.get(x-1).set(y+1, new Air(x-1, y+1));
			Global.blocks.get(x).set(y, new CraftingTable(x, y));
		} else if(Global.getBlock(x, y-1).type == type && Global.getBlock(x, y-1).blocksPlayer && 
				Global.getBlock(x-1, y).type == type && Global.getBlock(x-1, y).blocksPlayer &&
				Global.getBlock(x-1, y-1).type == type && Global.getBlock(x-1, y-1).blocksPlayer) {
			Global.blocks.get(x).set(y-1, new Air(x, y-1));
			Global.blocks.get(x-1).set(y, new Air(x-1, y));
			Global.blocks.get(x-1).set(y-1, new Air(x-1, y-1));
			Global.blocks.get(x).set(y, new CraftingTable(x, y));
		} else if(Global.getBlock(x, y+1).type == type && Global.getBlock(x, y+1).blocksPlayer && 
				Global.getBlock(x+1, y).type == type && Global.getBlock(x+1, y).blocksPlayer &&
				Global.getBlock(x+1, y+1).type == type && Global.getBlock(x+1, y+1).blocksPlayer) {
			Global.blocks.get(x).set(y+1, new Air(x, y+1));
			Global.blocks.get(x+1).set(y, new Air(x+1, y));
			Global.blocks.get(x+1).set(y+1, new Air(x+1, y+1));
			Global.blocks.get(x).set(y, new CraftingTable(x, y));
		} else if(Global.getBlock(x+1, y-1).type == type && Global.getBlock(x+1, y-1).blocksPlayer && 
				Global.getBlock(x+1, y).type == type && Global.getBlock(x+1, y).blocksPlayer &&
				Global.getBlock(x, y-1).type == type && Global.getBlock(x, y-1).blocksPlayer) {
			Global.blocks.get(x+1).set(y-1, new Air(x+1, y-1));
			Global.blocks.get(x+1).set(y, new Air(x+1, y));
			Global.blocks.get(x).set(y-1, new Air(x, y-1));
			Global.blocks.get(x).set(y, new CraftingTable(x, y));
		}
	}
	
	public static Block stringToBlock(String blockName, int x, int y) {
		Block o = null;
		try {
			Class<?> cl = Class.forName(blockName);
			Constructor<?> cons = cl.getConstructor(int.class, int.class);
			o = (Block) cons.newInstance(x, y);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return o;
	}
	
	public static Item stringToItem(String itemName, double x, double y) {
		Item o = null;
		try {
			Class<?> cl = Class.forName(itemName);
			Constructor<?> cons = cl.getConstructor(double.class, double.class);
			o = (Item) cons.newInstance(x, y);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return o;
	}
	
	public static Item blockToItem(String blockName, double x, double y) {
		return new BlockItem(blockName, x, y);
	}
	
	public static HashMap<String, Double> normalize(final double x, final double y) {
	    final double length = Math.sqrt(x * x + y * y);
	    return new HashMap<String, Double>() {{ put("x", x / length); put("y", y / length); }};
	}
}
