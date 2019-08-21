package general;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import blocks.*;
import entities.Player;

/** ExploreLand -- AryanM
 * @author Aryan M
 *
 *btw there is a scale method in Graphics... looks legit
 *Reduce lag:
 	*Generate only parts of a new column, depending on how much of it user can see
 		*So if user's view is 120 to 90, generate only those 30 blocks
 		*generateColumn(int x, int top, int bottom);
 */
public class World {
	
	public int startWidth = Global.settings.get("InitialWorldWidth");
	public int width = 0;
	public int height = Global.settings.get("WorldHeight");
	public int ground = 100;
	public double steepness = 2.3; //2 for 1-block variation.
	public ArrayList<Integer> grounds = new ArrayList<Integer>();
	//public String[] biomes = {"Ocean"};
	public String[] biomes = {"Plains", "Forest", "BirchForest", "SpruceForest", "OakForest", "DarkOakForest", "Desert", "Ocean"};
	public String biome = "Plains";
	public ArrayList<Integer> caves = new ArrayList<Integer>();
	
	public World() {}
	
	public void generateWorld() {
		ground = (int) (height*(2.0/3));//100
		for(int i = 0; i < 1; ++i) {
			caves.add((int) (Math.random()*(ground-8)));
		}
		
		int stevX = 0;
		if(startWidth >= 70) {
			stevX = ThreadLocalRandom.current().nextInt(34, startWidth - 35);
		} else if(startWidth > 35) {
			stevX = ThreadLocalRandom.current().nextInt(0, startWidth - 35);
		} else {
			stevX = 0;
		}
		int stevY = 0;
		for(int x = 0; x < startWidth; ++x) {
			generateColumn(x);
			if(x == stevX) {
				stevY = ground + 2;
			}
			
		}
		Global.player = new Player(stevX, stevY);
	}
	
	public void generateColumn(int x) {
		Global.blocks.add(new ArrayList<Block>());
		ground = ground + (int) (Math.random()*steepness) - (int) (Math.random()*steepness);
		grounds.add(ground);
		
		if(ground < 0) { ground = Math.abs(ground); ground++; } else
		if(ground + 20 >= height) { ground -= (int) (Math.random()*3); }
		if(x % 100 == 0) { biome = biomes[(int) (Math.random()*biomes.length)]; }
		if(biome.equals("Plains")) { steepness = 1.2; } else if(biome.equals("Ocean")) { steepness = 1.02; } else { steepness = 2.3; }
		if(caves.size() < 3 && (int) (Math.random()*50) == 0) {
			caves.add(ThreadLocalRandom.current().nextInt(ground - 40, ground - 10));
		}
		
		for(int y = 0; y < height; ++y) {
			if(y == ground) {
				if(biome.equals("Desert")) {
					Global.blocks.get(x).add(new Sand(x, y));
				} else if(biome.equals("Ocean")) {
					Global.blocks.get(x).add(new Water(x, y));
				} else {
					Global.blocks.get(x).add(new GrassBlock(x, y));
				}
				if(x > 6) { generateTree(x-3, grounds.get(x-3)+1, biome); }
			} else if(y < ground && y > ground - ((int) (Math.random()*3)) - 3) {
				if(biome.equals("Desert")) {
					if(y == ground - 1) { Global.blocks.get(x).add(new Sand(x, y)); } else {
						Global.blocks.get(x).add(new Sandstone(x, y));
					}
				} else if(biome.equals("Ocean")) {
					Global.blocks.get(x).add(new Water(x, y));
				} else {
					Global.blocks.get(x).add(new Dirt(x, y));
				}
			} else if(y < ground) {
				if(biome.equals("Ocean") && y > ground - (int) (Math.random()*3 + 30)) {
					if(y <= ground - 28) {
						Global.blocks.get(x).add(new Gravel(x, y));
					} else {
						Global.blocks.get(x).add(new Water(x, y));
					}
				} else {
					Global.blocks.get(x).add(new Stone(x, y));
					if((int) (Math.random()*100) == 0) {
						Generate.coalVein(x, y);
					}
					if((int) (Math.random()*250) == 0) {
						Generate.ironVein(x, y);
					}
					
					if(y <= 30) {
						if((int) (Math.random()*400) == 0) {
							Generate.goldVein(x, y);
						}
						
						if(y <= 16) {
							if((int) (Math.random()*1000) == 0) {
								Generate.diamondVein(x, y);
							}
							
							if(y <= 2) {
								if((int) (Math.random()*3) == 0) {
									Global.blocks.get(x).set(y, new Bedrock(x, y));
								}
								if(y == 1 && (int) (Math.random()*2) == 0) {
									Global.blocks.get(x).set(y, new Bedrock(x, y));
								}
								if(y == 0) {
									Global.blocks.get(x).set(y, new Bedrock(x, y));
								}
							}
						}
					}
				}
			} else {
				Global.blocks.get(x).add(new Air(x, y));
			}
			
			for(int i = 0; i < caves.size(); ++i) {
				if(y == caves.get(i)) {
					int caveHeight = ThreadLocalRandom.current().nextInt(1, 2);
					for(int j = 6; j > caveHeight; --j) {
						if(y-j > 0) {
							Global.blocks.get(x).set(y-j, new Air(x, y-j));
						}
					}
					caves.set(i, caves.get(i) + (int) (Math.random()*2) - (int) (Math.random()*2));//3
					if(y >= ground + 2) {
						caves.remove(i);
					}
				}
			}
		}
		width++;
	}
	
	public void generateTree(int x, int y, String biome) {
		
		if(biome == "Forest" && (int) (Math.random()*10) == 0) {
			//Normal
			if((int) (Math.random()*2) == 0) {
				Generate.OakTree(x, y);
			} else {
				Generate.BirchTree(x, y);
			}
		} else if(biome == "OakForest" && (int) (Math.random()*10) == 0) {
			Generate.OakTree(x, y);
		} else if(biome == "BirchForest" && (int) (Math.random()*10) == 0) {
			Generate.BirchTree(x, y);
		} else if(biome == "DarkOakForest" && (int) (Math.random()*3.5) == 0) {
			//Thick
			Generate.DarkOakTree(x, y);
		} else if(biome == "SpruceForest" && (int) (Math.random()*5) == 0) {
			//Tall
			Generate.SpruceTree(x, y);
		}
	}
}