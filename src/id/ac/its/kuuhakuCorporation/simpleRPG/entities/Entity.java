 package id.ac.its.kuuhakuCorporation.simpleRPG.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Arrow;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Player;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Zombie;
import id.ac.its.kuuhakuCorporation.simpleRPG.states.State;
import id.ac.its.kuuhakuCorporation.simpleRPG.utils.Sound;

public abstract class Entity {

	protected int drop;
	public static final int DEFAULT_HEALTH = 20,
							DEFAULT_DAMAGE = 1;
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected int health;
	protected boolean active = true;
	protected int damage;

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;

		bounds = new Rectangle(0, 0, width, height);
		damage = DEFAULT_DAMAGE;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void die();

	public void hurt(int dmg) {

		health -= dmg;
		if(health<=0) {
			active = false;
			die();
		}
	}

	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		boolean zombieExist = false;

		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) continue;

			if((e instanceof Zombie && e.isActive()) || (this instanceof Zombie && this.isActive())) zombieExist = true;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && this instanceof Arrow && this.active && e.active) {
				if(!(e instanceof Player)) {
					Sound.pHit.play();
					e.hurt(this.damage);
				}
				this.active = false;
			}
			else if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.isActive() && this.isActive())
				return true;
		}
		
		if(!zombieExist) {
			Sound.zDie.stop();
			Sound.main.stop();
			Sound.win.play();
			handler.con=1;
			handler.getWorld().setnZombie();
			handler.getGame().gameState.reset();
			State.setState(handler.getGame().menuState);
		}
		
		return false;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle( (int) (x + bounds.x + xOffset),
				(int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
