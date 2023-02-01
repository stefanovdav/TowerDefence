package td.managers;

import td.enemies.Enemy;
import td.helper.LoadSave;
import td.objects.Projectile;
import td.objects.Tower;
import td.scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static td.helper.Constants.Projectiles.*;
import static td.helper.Constants.Towers.*;

public class ProjectileManager {
	private final Playing playing;
	private final List<Projectile> projectiles = new ArrayList<>();
	private List<BufferedImage> atkImgs;

	public ProjectileManager(Playing playing) {
		this.playing = playing;
		uploadImgs();
	}

	public void newProjectile(Tower t, Enemy e) {
		int type = getProjType(t);

		int xDist = (int) Math.abs(t.getX() - e.getX());
		int yDist = (int) Math.abs(t.getY() - e.getY());
		int totDist = xDist + yDist;

		float xPer = (float) xDist / totDist;

		float xSpeed = xPer * td.helper.Constants.Projectiles.getSpeed(type);
		float ySpeed = td.helper.Constants.Projectiles.getSpeed(type) - xSpeed;

		if (t.getX() > e.getX()) xSpeed *= -1;
		if (t.getY() > e.getY()) ySpeed *= -1;
		int proj_id = 0;

		projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), proj_id++, type));

	}

	public void update() {
		for (Projectile p : projectiles)
			if (p.isActive()) {
				p.move();
				if (isProjHittingEnemy(p)) {
					p.setActive(false);
				} else if (isProjectileGone(p)) {
					p.setActive(false);
				}
			}
	}

	private boolean isProjectileGone(Projectile p) {
		return !(p.getPos().x >= 0 && p.getPos().x <= 640 && p.getPos().y >= 0 && p.getPos().y <= 800);

	}

	private boolean isProjHittingEnemy(Projectile p) {
		for (Enemy e : playing.getEnemyManger().getEnemies()) {
			if (e.getBounds().contains(p.getPos())) {
				e.hurt((int) p.getDmg());
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for (Projectile p : projectiles)
			if (p.isActive()) {
				g.drawImage(atkImgs.get(p.getProjectileType()), (int) p.getPos().x, (int) p.getPos().y, null);
			}
	}

	private void uploadImgs() {
		atkImgs = LoadSave.getProjectiles();
	}

	private int getProjType(Tower t) {
		switch (t.getTowerType()) {
			case ELON -> {
				return TWEET;
			}
			case WARREN -> {
				return DOLLAR;
			}
			case SAM -> {
				return FTX;
			}
			//TODO:Think of what sz would shoot
			case SZ -> {
				return FTX;
			}
		}
		return 0;
	}
}
