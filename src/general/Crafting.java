package general;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Crafting {

public static ArrayList<CraftButton> buttons = new ArrayList<CraftButton>();
public static boolean isOpen;
public static LinkedHashMap<String, ArrayList<LinkedHashMap<String, Integer>>> recipes = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, Integer>>>();

public static void addRecipes() {
	
	recipes.put("blocks.OakPlanks", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("blocks.OakLog", 1);
		}});
	}});
	
	recipes.put("blocks.BirchPlanks", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("blocks.BirchLog", 1);
		}}); 
	}});
	
	recipes.put("blocks.SprucePlanks", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("blocks.SpruceLog", 1);
		}});
	}});
	
	recipes.put("blocks.DarkOakPlanks", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("blocks.DarkOakLog", 1);
		}});
	}});
	recipes.put("blocks.CraftingTable", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.OakPlanks", 4);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.BirchPlanks", 4);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.SprucePlanks", 4);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.DarkOakPlanks", 4);
		}});
	}});
	
	recipes.put("blocks.Furnace", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.Cobblestone", 8);
		}});
	}});
	
	recipes.put("items.Stick", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("blocks.OakPlanks", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("blocks.BirchPlanks", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("blocks.SprucePlanks", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("blocks.DarkOakPlanks", 2);
		}});
	}});
	
	recipes.put("blocks.Sandstone", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.Sand", 4);
		}});
	}});
	
	recipes.put("items.Shears", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.IronIngot", 2);
		}});
	}});
	
	recipes.put("items.WoodenPickaxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.OakPlanks", 3);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.BirchPlanks", 3);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.SprucePlanks", 3);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.DarkOakPlanks", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.StonePickaxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.Cobblestone", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.IronPickaxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.IronIngot", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.GoldenPickaxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.GoldIngot", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.DiamondPickaxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.Diamond", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.WoodenAxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.OakPlanks", 3);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.BirchPlanks", 3);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.SprucePlanks", 3);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.DarkOakPlanks", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.StoneAxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.Cobblestone", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.IronAxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.IronIngot", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.GoldenAxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.GoldIngot", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.DiamondAxe", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.Diamond", 3);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.WoodenShovel", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.OakPlanks", 1);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.BirchPlanks", 1);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.SprucePlanks", 1);
			put("items.Stick", 2);
		}});
		
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.DarkOakPlanks", 1);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.StoneShovel", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("blocks.Cobblestone", 1);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.IronShovel", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.IronIngot", 1);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.GoldenShovel", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.GoldIngot", 1);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("items.DiamondShovel", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.Diamond", 1);
			put("items.Stick", 2);
		}});
	}});
	
	recipes.put("blocks.CoalBlock", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.Coal", 9);
		}});
	}});
	
	recipes.put("blocks.IronBlock", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.IronIngot", 9);
		}});
	}});
	
	recipes.put("blocks.GoldBlock", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.GoldIngot", 9);
		}});
	}});
	
	recipes.put("blocks.DiamondBlock", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 1);
			put("items.Diamond", 9);
		}});
	}});
	
	recipes.put("items.Coal", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 9);
			put("blocks.CoalBlock", 1);
		}});
	}});
	
	recipes.put("items.IronIngot", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 9);
			put("blocks.IronBlock", 1);
		}});
	}});
	
	recipes.put("items.GoldIngot", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 9);
			put("blocks.GoldBlock", 1);
		}});
	}});
	
	recipes.put("items.Diamond", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 9);
			put("blocks.DiamondBlock", 1);
		}});
	}});
	
	recipes.put("blocks.Torch", new ArrayList<LinkedHashMap<String, Integer>>() {{
		add(new LinkedHashMap<String, Integer>() {{
			put("amount", 4);
			put("items.Coal", 1);
			put("items.Stick", 1);
		}});
	}});
}

public void openButtons() {
	Smelting.isOpen = false;
	isOpen = true;
	buttons.clear();
	int temp = 0;
	for(String item : Crafting.recipes.keySet()) { //have a for loop through the ArrayList and add a button for EVERY craftable recipe in the list.
		if(!Crafting.canCraft(item).isEmpty()) { //maybe an integer 2nd argument for which recipe to check...
			for(int i = 0; i < Crafting.recipes.get(item).size(); ++i) {
				if(!Crafting.canCraft(item).contains(Crafting.recipes.get(item).get(i))) { continue; }
				buttons.add(new CraftButton(item, i, 450, 50+temp*50));
				temp++;
			}
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
		g.drawString("CRAFTING", 800, 20);
		for(int i = 0; i < buttons.size(); ++i) {
			buttons.get(i).draw(g);
		}
	}
}

public static ArrayList<LinkedHashMap<String, Integer>> canCraft(String item) {
	ArrayList<LinkedHashMap<String, Integer>> craftableRecipes = new ArrayList<LinkedHashMap<String, Integer>>();
	
	for(int i = 0; i < recipes.get(item).size(); ++i) {
		LinkedHashMap<String, Integer> recipe = recipes.get(item).get(i);
		boolean can = true;
		for(String ingredient : recipe.keySet()) {
			if(ingredient == "amount") { continue; } //loop through ArrayList??
			
			if(!Global.toolbar.hasItem(ingredient, recipe.get(ingredient))) {
				can = false;
			}
		}
		
		if(can) { craftableRecipes.add(recipe); }
	}
	
	return craftableRecipes;
}

public static void craft(String item, int recipeIndex) {
	if(!canCraft(item).isEmpty()) {
		LinkedHashMap<String, Integer> recipe = recipes.get(item).get(recipeIndex);
		
		for(String ingredient : recipe.keySet()) {
			if(ingredient != "amount") {
				Global.toolbar.removeItem(ingredient, recipe.get(ingredient));
			}
		}
		
		Global.toolbar.giveItem(item, recipe.get("amount"));
		
		Global.crafter.openButtons();
	}
}

public class CraftButton {
	String item = "";
	int recipeIndex = 0;
	int x = 0;
	int y = 0;
	int width = 900;
	int height = 50;
	boolean isHovered;
	Image image;
	
	public CraftButton(String itemToCraft, int newRecipeIndex, int xPixel, int yPixel) {
		item = itemToCraft;
		x = xPixel;
		y = yPixel;
		recipeIndex = newRecipeIndex;
		try {
			image = new Image("resources/" + item.replaceAll("\\.", "/") + ".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setFilter(Image.FILTER_NEAREST);
	}
	
	public void draw(Graphics g) {
		if(isHovered) {
			g.setColor(Color.gray);
			g.fillRect(x, y, width, height);
		}
		g.setColor(Color.white);
		g.fillRect(x + 5, y + 5, width - 10, height - 10);
		image.draw(x, y, height, height);
		g.setColor(Color.black);
		g.drawString(item.split("\\.")[1], x + height + 10, y + 10);
		g.drawString(recipes.get(item).get(recipeIndex) + "", x + height + 10, y + 25);
		g.setColor(Color.green);
		g.drawString(recipes.get(item).get(recipeIndex).get("amount") + "", x + width - 50, y + 10);
	}
	
	public void update(Input input) {
		int xpos = input.getMouseX();
		int ypos = input.getMouseY();
		
		if(input.isKeyDown(Input.KEY_W)) {
			y -= 1;
		}
		if(input.isKeyDown(Input.KEY_S)) {
			y += 1;
		}
		if(xpos > x && xpos < x + width && ypos > y && ypos < y + height) {
			isHovered = true;
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
				isClicked(input);
			}
			
		} else if(isHovered == true){
			isHovered = false;
		}
	}
	
	public void isClicked(Input input) {
		if(!input.isKeyDown(Input.KEY_LSHIFT)) {
			Crafting.craft(item, recipeIndex);
		} else {
			while(canCraft(item).contains(recipes.get(item).get(recipeIndex))) {
				Crafting.craft(item, recipeIndex);
			}
		}
	}
}
}
