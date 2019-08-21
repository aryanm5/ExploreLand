package general;

import blocks.*;

public class Generate {
	
	public static void ironVein(int x, int y) {
		Global.blocks.get(x).set(y, new IronOre(x, y));
		if(x > 2 && y > 2) {
			for(int i = 0, total = (int) (Math.random()*12); i < total; ++i) {
				int randomX = (int) (Math.random()*3);
				int randomY = (int) (Math.random()*3);
				Global.blocks.get(x-randomX).set(y-randomY, new IronOre(x-randomX, y-randomY));
			}
		}
	}
	
	public static void coalVein(int x, int y) {
		Global.blocks.get(x).set(y, new CoalOre(x, y));
		if(x > 3 && y > 3) {
			for(int i = 0, total = (int) (Math.random()*16); i < total; ++i) {
				int randomX = (int) (Math.random()*4);
				int randomY = (int) (Math.random()*4);
				Global.blocks.get(x-randomX).set(y-randomY, new CoalOre(x-randomX, y-randomY));
			}
		}
	}
	
	public static void goldVein(int x, int y) {
		Global.blocks.get(x).set(y, new GoldOre(x, y));
		if(x > 2 && y > 2) {
			for(int i = 0, total = (int) (Math.random()*6); i < total; ++i) {
				int randomX = (int) (Math.random()*2.5);
				int randomY = (int) (Math.random()*2.5);
				Global.blocks.get(x-randomX).set(y-randomY, new GoldOre(x-randomX, y-randomY));
			}
		}
	}
	
	public static void diamondVein(int x, int y) {
		Global.blocks.get(x).set(y, new DiamondOre(x, y));
		if(x > 2 && y > 2) {
			for(int i = 0, total = (int) (Math.random()*12); i < total; ++i) {
				int randomX = (int) (Math.random()*2);
				int randomY = (int) (Math.random()*2);
				Global.blocks.get(x-randomX).set(y-randomY, new DiamondOre(x-randomX, y-randomY));
			}
		}
	}

	public static void OakTree(int x, int y) {
		Global.blocks.get(x).set(y, new OakLog(x, y, false));
		Global.blocks.get(x).set(y+1, new OakLog(x, y+1, false));
		Global.blocks.get(x).set(y+2, new OakLog(x, y+2, false));
		Global.blocks.get(x).set(y+3, new Leaves(x, y+3, false));
		Global.blocks.get(x-2).set(y+3, new Leaves(x-2, y+3, false));
		Global.blocks.get(x-1).set(y+3, new Leaves(x-1, y+3, false));
		Global.blocks.get(x+1).set(y+3, new Leaves(x+1, y+3, false));
		Global.blocks.get(x+2).set(y+3, new Leaves(x+2, y+3, false));
		
		Global.blocks.get(x-1).set(y+4, new Leaves(x-1, y+4, false));
		Global.blocks.get(x).set(y+4, new Leaves(x, y+4, false));
		Global.blocks.get(x+1).set(y+4, new Leaves(x+1, y+4, false));
	}
	
	public static void BirchTree(int x, int y) {
		Global.blocks.get(x).set(y, new BirchLog(x, y, false));
		Global.blocks.get(x).set(y+1, new BirchLog(x, y+1, false));
		Global.blocks.get(x).set(y+2, new BirchLog(x, y+2, false));
		Global.blocks.get(x).set(y+3, new Leaves(x, y+3, false));
		Global.blocks.get(x-1).set(y+3, new Leaves(x-1, y+3, false));
		Global.blocks.get(x+1).set(y+3, new Leaves(x+1, y+3, false));
		
		Global.blocks.get(x-1).set(y+4, new Leaves(x-1, y+4, false));
		Global.blocks.get(x).set(y+4, new Leaves(x, y+4, false));
		Global.blocks.get(x+1).set(y+4, new Leaves(x+1, y+4, false));
		Global.blocks.get(x).set(y+5, new Leaves(x, y+5, false));
	}
	
	public static void DarkOakTree(int x, int y) {
		Global.blocks.get(x).set(y, new DarkOakLog(x, y, false));
		Global.blocks.get(x-1).set(y, new DarkOakLog(x-1, y, false));
		
		Global.blocks.get(x).set(y+1, new DarkOakLog(x, y+1, false));
		Global.blocks.get(x-1).set(y+1, new DarkOakLog(x-1, y+1, false));
		
		Global.blocks.get(x).set(y+2, new DarkOakLog(x, y+2, false));
		Global.blocks.get(x-1).set(y+2, new DarkOakLog(x-1, y+2, false));
		
		Global.blocks.get(x).set(y+3, new DarkOakLog(x, y+3, false));
		Global.blocks.get(x-1).set(y+3, new DarkOakLog(x-1, y+3, false));
		Global.blocks.get(x-2).set(y+3, new Leaves(x-2, y+3, false));
		Global.blocks.get(x+1).set(y+3, new Leaves(x+1, y+3, false));
		
		Global.blocks.get(x-3).set(y+4, new Leaves(x-3, y+4, false));
		Global.blocks.get(x-2).set(y+4, new Leaves(x-2, y+4, false));
		Global.blocks.get(x-1).set(y+4, new Leaves(x-1, y+4, false));
		Global.blocks.get(x).set(y+4, new Leaves(x, y+4, false));
		Global.blocks.get(x+1).set(y+4, new Leaves(x+1, y+4, false));
		Global.blocks.get(x+2).set(y+4, new Leaves(x+2, y+4, false));
		
		Global.blocks.get(x-2).set(y+5, new Leaves(x-2, y+5, false));
		Global.blocks.get(x-1).set(y+5, new Leaves(x-1, y+5, false));
		Global.blocks.get(x).set(y+5, new Leaves(x, y+5, false));
		Global.blocks.get(x+1).set(y+5, new Leaves(x+1, y+5, false));
		
		Global.blocks.get(x-1).set(y+6, new Leaves(x-1, y+6, false));
		Global.blocks.get(x).set(y+6, new Leaves(x, y+6, false));
	}
	
	public static void SpruceTree(int x, int y) {
		Global.blocks.get(x).set(y, new SpruceLog(x, y, false));
		Global.blocks.get(x).set(y+1, new SpruceLog(x, y+1, false));
		Global.blocks.get(x).set(y+2, new SpruceLog(x, y+2, false));
		Global.blocks.get(x).set(y+3, new SpruceLog(x, y+3, false));
		Global.blocks.get(x).set(y+4, new SpruceLog(x, y+4, false));
		Global.blocks.get(x).set(y+5, new SpruceLog(x, y+5, false));
		
		Global.blocks.get(x).set(y+6, new Leaves(x, y+6, false));
		Global.blocks.get(x-1).set(y+6, new Leaves(x-1, y+6, false));
		Global.blocks.get(x+1).set(y+6, new Leaves(x+1, y+6, false));
		
		Global.blocks.get(x).set(y+7, new Leaves(x, y+7, false));
	}
}
