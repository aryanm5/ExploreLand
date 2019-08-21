package entities;

import org.newdawn.slick.Input;

import general.Global;

public class Mob extends Entity {
	public double health;
	public int maxHealth;
	public int oxygen = 10;
	public int maxOxygen = 10;
	public boolean drowns = true;
	public double distanceFallen;
	public boolean takesFallDamage = true;
	public float brightness = 1f;
	
	public Mob(double x, double y) {
		super(x, y);
	}
	
	public void update(Input input) {
		super.update(input);
		distanceFallen += yVelocity;
		if(Global.getBlock((int) (x + 0.5), (int) (y - yVelocity)).blocksPlayer) {
			//fall damage
			if(takesFallDamage && (int) distanceFallen > 3) {
				//fall damage
				reduceHealth((int) distanceFallen - 3);
			}
			distanceFallen = 0;
		}
		
		updateOxygen();
		
		if(y < 0 && Global.time % 100 == 0) {
			reduceHealth(3);
		}
	}
	public void goTowardsTarget() {
		
	}
	public void getTarget() {
		
	}
	
	public void updateOxygen() {
		if(submerged()) {
			if(oxygen <= 0 && Global.time % 100 == 0) {
				reduceHealth(2);
			}
			if(Global.time % 100 == 0) {
				reduceOxygen(1);
			}
		} else {
			if(oxygen < maxOxygen && Global.time % 40 == 0) {
				increaseOxygen(1);
			}
		}
	}
	public boolean submerged() {
		return (Global.getBlock((int) x, (int) y).drownsMobs);
	}
	public void reduceOxygen(int amount) {
		oxygen -= amount;
		if(oxygen < 0) { oxygen = 0; }
	}
	public void increaseOxygen(int amount) {
		oxygen += amount;
		if(oxygen > maxOxygen) { oxygen = maxOxygen; }
	}
	public void reduceHealth(double amount) {
		health -= amount;
		if(health < 0) { health = 0; }
		
		startDamageAnimation();
	}
	public void reduceHealth(double amount, boolean armorReduces) {
		//reduce amount depending on armor
		reduceHealth(amount);
	}
	public void addHealth(double amount) {
		health += amount;
		if(health > maxHealth) { health = maxHealth; }
	}
	public void setMaxHealth(int newHealth) {
		maxHealth = newHealth;
		health = maxHealth;
	}
	public void setBrightness(float newBrightness) {
		if(brightness != newBrightness) {
			brightness = newBrightness;
			image.setImageColor(brightness, brightness, brightness);
		}
	}
	public void startDamageAnimation() {
		image.setImageColor(brightness, 0.1f, 0.1f);
		Thread damageAnimation = new Thread(new DamageAnimation(this));
		damageAnimation.start();
	}
	public void endDamageAnimation() {
		image.setImageColor(brightness, brightness, brightness);
	}
	
	public class DamageAnimation implements Runnable {
		Mob mob;
		public DamageAnimation(Mob mob) {
			this.mob = mob;
		}
		public void run() {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mob.endDamageAnimation();
		}
	}
}
