package id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.text.html.parser.Entity;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Animation;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;

public class Player extends Creature {

	private Animation animDown,animUp,animLeft,animRight;
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
		animDown = new Animation(500, Assets.player_down);
		animUp= new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
	}

	@Override
	public void tick() {
		
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		getInput();
		move();
		handler.getGameCamera().certerOnEntity(this);
		checkAttacks();
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		Rectangle col = getCollisionBounds(0, 0);
		Rectangle att = new Rectangle();
		int attSize = 20;
		att.width = attSize;
		att.height = attSize;
		
		if(handler.getKeyManager().aUp) {
			att.x = col.x + col.width/2 - attSize/2;
			att.y = col.y - attSize;
		}
		else if(handler.getKeyManager().aDown) {
			att.x = col.x + col.width/2 - attSize/2;
			att.y = col.y + col.height;
		}
		else if(handler.getKeyManager().aLeft) {
			att.y = col.y + col.height/2 - attSize/2;
			att.x = col.x - attSize;
		}
		else if(handler.getKeyManager().aRight) {
			att.y = col.y + col.height/2 - attSize/2;
			att.x = col.x + col.width;
		}
		else
			return;
		
		attackTimer = 0;
		
		for(id.ac.its.kuuhakuCorporation.simpleRPG.entities.Entity e: handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) continue;
			else if(e.getCollisionBounds(0,0).intersects(att)) {
				e.hurt(1);
				return;
			}
		}
	}
	
	@Override
	public void die() {
		System.out.println("YOU Lose");
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove <0)
			return animLeft.getCurrentFrame();
		else if(xMove>0)
			return animRight.getCurrentFrame();
		else if(yMove<0)
			return animUp.getCurrentFrame();
		else
			return animDown.getCurrentFrame();
	}
	
}
