package td.objects;

import java.awt.geom.Point2D;

public class Projectile {
	private Point2D.Float pos;
	private final int id;
	private final int projectileType;
	private final int dmg;
	private boolean active;
	private final float xSpeed;
	private final float ySpeed;

	public Projectile(float x, float y, float xSpeed, float ySpeed, int dmg, int id, int projectileType) {
		pos = new Point2D.Float(x,y);
		this.id = id;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.dmg = dmg;
		this.projectileType = projectileType;
		active = true;
	}

	public void move() {
		pos.x += xSpeed;
		pos.y += ySpeed;
	}

	public Point2D.Float getPos() {
		return pos;
	}

	public int getId() {
		return id;
	}

	public int getProjectileType() {
		return projectileType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getDmg() {
		return dmg;
	}
}
