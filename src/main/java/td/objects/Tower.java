package td.objects;

import td.helper.Constants;

import java.awt.image.BufferedImage;

import static td.helper.Constants.Towers.*;

public class Tower {
	private final int x;
	private final int y;
	private final int id;
	private final int towerType;
	private int cdTick;
	private int dmg;
	private int tier;
	private float range, cooldown;

	public Tower(int x, int y, int id, int towerType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		tier = 1;
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	}

	public void upgrade() {
		this.tier++;

		switch (towerType) {
			case ELON -> {
				dmg += 2;
				range += 20;
				cooldown -= 1;
			}
			case WARREN -> {
				dmg += 3;
				range += 10;
				cooldown -= 1;
			}
			case SAM -> {
				dmg += 1;
				range += 20;
				cooldown -= 1;}
			case SZ -> {
				dmg += 1.3;
				range += 20;
				cooldown -= 1;
			}

		}
	}

	public void update() {
		cdTick++;
	}

	public boolean isCooldownOver() {
		return cdTick >= cooldown;
	}

	public void resetCooldown() {
		cdTick = 0;
	}

	private void setDefaultCooldown() {
		cooldown = Constants.Towers.getShootCooldown(towerType);
	}

	private void setDefaultRange() {
		range = Constants.Towers.getStartRange(towerType);
	}

	private void setDefaultDmg() {
		dmg = Constants.Towers.getStartDmg(towerType);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}

	public int getTowerType() {
		return towerType;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRange() {
		return range;
	}

	public int getTier() {
		return tier;
	}
}
