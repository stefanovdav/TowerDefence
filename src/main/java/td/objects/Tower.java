package td.objects;

import td.helper.Constants;

import java.awt.image.BufferedImage;

import static td.helper.Constants.Towers.*;

public class Tower {
	private int x, y, id, towerType, cdTick, dmg, tier;
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
				dmg += 2;
				range += 20;
				cooldown -= 1;
			}
			case SAM -> {
				dmg += 2;
				range += 20;
				cooldown -= 1;}
			case SZ -> {
				dmg += 2;
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

	public float getCooldown() {
		return cooldown;
	}

	public int getTier() {
		return tier;
	}
}
