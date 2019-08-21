package general;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Smelting {
	
	public static ArrayList<SmeltButton> buttons = new ArrayList<SmeltButton>();
	public static boolean isOpen;
	public static LinkedHashMap<String, LinkedHashMap<String, String>> recipes = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	
	public void openButtons() {
		Crafting.isOpen = false;
		isOpen = true;
		buttons.clear();
		if(!Global.toolbar.hasItem("items.Coal")) { return; }
		int temp = 0;
		for(String item : Smelting.recipes.keySet()) { //have a for loop through the ArrayList and add a button for EVERY craftable recipe in the list.
			if(Smelting.canSmelt(item)) { //maybe an integer 2nd argument for which recipe to check...
				buttons.add(new SmeltButton(item, Smelting.recipes.get(item).get("output"), Integer.parseInt(Smelting.recipes.get(item).get("amount")), 450, 50+temp*50));
				temp++;
			}
		}
	}
	
	public static void update(Input input) {
		if(isOpen) {
			if(input.isKeyDown(Input.KEY_E) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_ESCAPE)) {
				isOpen = false;
			}
			for(int i = 0; i < buttons.size(); ++i) {
				buttons.get(i).update(input);
			}
		}
	}

	public static void drawButtons(Graphics g) {
		if(isOpen) {
			g.setColor(Color.lightGray);
			g.fillRect(450, 10, 900, 40);
			g.setColor(Color.black);
			g.drawString("SMELTING", 800, 20);
			if(!Global.toolbar.items.containsKey("items.Coal")) {
				g.setColor(Color.white);
				g.fillRect(450, 50, 900, 40);
				g.setColor(Color.black);
				g.drawString("Need Coal", 800, 60);
			}
			for(int i = 0; i < buttons.size(); ++i) {
				buttons.get(i).draw(g);
			}
		}
	}
	public static void smelt(String item) {
		if(!canSmelt(item)) { return; }
		
		Global.toolbar.removeItem(item, 1);
		Global.toolbar.giveItem(recipes.get(item).get("output"), Integer.parseInt(recipes.get(item).get("amount")));
		Global.toolbar.removeItem("items.Coal", 1);
		Global.smelter.openButtons();
	}
	
	public static boolean canSmelt(String item) {
		if(recipes.containsKey(item) && Global.toolbar.hasItem(item) && Global.toolbar.hasItem("items.Coal")) {
			return true;
		}
		
		return false;
	}
	public static void addRecipes() {
		recipes.put("blocks.Sand", new LinkedHashMap<String, String>() {{
			put("amount", "1");
			put("output", "blocks.Glass");
		}});
		recipes.put("blocks.IronOre", new LinkedHashMap<String, String>() {{
			put("amount", "1");
			put("output", "items.IronIngot");
		}});
		recipes.put("blocks.GoldOre", new LinkedHashMap<String, String>() {{
			put("amount", "1");
			put("output", "items.GoldIngot");
		}});
		recipes.put("blocks.GoldOre", new LinkedHashMap<String, String>() {{
			put("amount", "1");
			put("output", "items.GoldIngot");
		}});
		recipes.put("blocks.OakLog", new LinkedHashMap<String, String>() {{
			put("amount", "2");
			put("output", "items.Coal");
		}});
		recipes.put("blocks.BirchLog", new LinkedHashMap<String, String>() {{
			put("amount", "2");
			put("output", "items.Coal");
		}});
		recipes.put("blocks.SpruceLog", new LinkedHashMap<String, String>() {{
			put("amount", "2");
			put("output", "items.Coal");
		}});
		recipes.put("blocks.DarkOakLog", new LinkedHashMap<String, String>() {{
			put("amount", "2");
			put("output", "items.Coal");
		}});
		recipes.put("blocks.CoalOre", new LinkedHashMap<String, String>() {{
			put("amount", "2");
			put("output", "items.Coal");
		}});
		recipes.put("blocks.DiamondOre", new LinkedHashMap<String, String>() {{
			put("amount", "1");
			put("output", "items.Diamond");
		}});
		recipes.put("blocks.Cobblestone", new LinkedHashMap<String, String>() {{
			put("amount", "1");
			put("output", "blocks.Stone");
		}});
		recipes.put("blocks.Stone", new LinkedHashMap<String, String>() {{
			put("amount", "1");
			put("output", "blocks.SmoothStone");
		}});
	}
	
	public class SmeltButton {
		String input = "";
		String output = "";
		int x = 0;
		int y = 0;
		int width = 900;
		int height = 50;
		boolean isHovered;
		Image outImage;
		Image inImage;
		int smeltAmount = 1;
		
		public SmeltButton(String input1, String output1, int smeltAmount1, int xPixel, int yPixel) {
			input = input1;
			output = output1;
			smeltAmount = smeltAmount1;
			x = xPixel;
			y = yPixel;
			try {
				outImage = new Image(output.replaceAll("\\.", "//") + ".png");
				inImage = new Image(input.replaceAll("\\.", "//") + ".png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outImage.setFilter(Image.FILTER_NEAREST);
			inImage.setFilter(Image.FILTER_NEAREST);
		}
		
		public void draw(Graphics g) {
			if(isHovered) {
				g.setColor(Color.gray);
				g.fillRect(x, y, width, height);
			}
			g.setColor(Color.white);
			g.fillRect(x + 5, y + 5, width - 10, height - 10);
			outImage.draw(x, y, height, height);
			inImage.draw(x + width/2, y, height, height);
			g.setColor(Color.black);
			g.drawString(smeltAmount + " " + output.split("\\.")[1], x + height + 10, y + 10);
			g.drawString("from 1 " + input.split("\\.")[1], x + height + 10, y + 25);
		}
		
		public void update(Input ginput) {
			int xpos = ginput.getMouseX();
			int ypos = ginput.getMouseY();
			
			if(ginput.isKeyDown(Input.KEY_W)) {
				y -= 1;
			}
			if(ginput.isKeyDown(Input.KEY_S)) {
				y += 1;
			}
			
			if(xpos > x && xpos < x + width && ypos > y && ypos < y + height) {
				isHovered = true;
				if(ginput.isMousePressed(ginput.MOUSE_LEFT_BUTTON)) {
					isClicked(ginput);
				}
				
			} else if(isHovered == true){
				isHovered = false;
			}
		}
		
		public void isClicked(Input ginput) {
			if(!ginput.isKeyDown(Input.KEY_LSHIFT)) {
				Smelting.smelt(input);
			} else {
				while(canSmelt(input)) {
					Smelting.smelt(input);
				}
			}
		}
	}
}
