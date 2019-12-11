package id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.tiles.Tile;

public class Arrow extends Creature {
	private BufferedImage image;
	protected float xMove, yMove;
	
	public Arrow(Handler handler, float x, float y, BufferedImage image) {
		super(handler, x, y, 50, 50);
		this.image = image;
		if(image.equals(Assets.arrow_up)) {
			yMove -= 5;
			this.y -= 64;
		}
		else if(image.equals(Assets.arrow_down)) {
			yMove += 5;
			this.y += 64;
		}
		else if(image.equals(Assets.arrow_left)) {
			xMove -= 5;
			this.x -= 64;
		}
		else {
			xMove += 5;
			this.x += 64;
		}
		this.active = true;
	}

	@Override
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getUpPower()) {
			damage = 2;
		}else {
			damage = 1;
		}
		
		move();
	}

	@Override
	public void render(Graphics g) {
		if(this.active)
			g.drawImage(this.image, (int)(x- handler.getGameCamera().getxOffset()),(int)(y- handler.getGameCamera().getyOffset()), width,height,null);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}
	
	public void moveX() {
		if(xMove > 0 ) {
			int tx = (int) (x + xMove + bounds.x +bounds.width) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}else {
				this.active = false;
			}
			
		}else if(xMove < 0) {
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}else {
				this.active = false;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0 ) {
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}else {
				this.active = false;
			}
			
		}else if(yMove > 0) {
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}else {
				this.active = false;
			}
		}
	}

}
