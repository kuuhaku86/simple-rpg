package id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Animation;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.items.Item;
import id.ac.its.kuuhakuCorporation.simpleRPG.utils.Sound;

public class Zombie extends Creature {

	private Animation animDown,animUp,animLeft,animRight;
	private int countStep;

	public Zombie(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 32;
		bounds.height = 32;
		health = 3;
		countStep = 0;

		animDown = new Animation(500, Assets.zombie_down);
		animUp= new Animation(500, Assets.zombie_up);
		animLeft = new Animation(500, Assets.zombie_left);
		animRight = new Animation(500, Assets.zombie_right);
	}

	@Override
	public void tick() {
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		randomMove();
		move();
	}

	@Override
	public void render(Graphics g) {
		if(this.isActive()) {
			g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			switch(health) {
				case 3:
					g.drawImage(Assets.health_bar_full,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset() - 50), width, height, null);
					break;
				case 2:
					g.drawImage(Assets.health_bar_23,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset() - 50), width, height, null);
					break;
				case 1:
					g.drawImage(Assets.health_bar_13,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset() - 50), width, height, null);
			}
		}
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

	public void randomMove() {
		float playerX = handler.getWorld().getEntityManager().getPlayer().getX();
		float playerY = handler.getWorld().getEntityManager().getPlayer().getY();

		float range = (float) Math.sqrt(Math.pow((playerX - this.x),2) + Math.pow((playerY - this.y),2));

		if(countStep > 25) {
			xMove = 0;
			yMove = 0;
			countStep = 0;
		}

		if(!(xMove > 1 || xMove < -1 || yMove > 1 || yMove < -1)) {


			if(range < 300) {
				if(this.x < playerX) xMove +=1;
				if(this.x > playerX) xMove -=1;
				if(this.y < playerY) yMove +=1;
				if(this.y > playerY) yMove -=1;
			}

			else {
				int randomDirection = (int)(Math.random() * 4 + 1);
				switch(randomDirection) {
					case 1 :
						yMove -= 1;
						break;
					case 2 :
						yMove += 1;
						break;
					case 3 :
						xMove -= 1;
						break;
					case 4 :
						xMove += 1;
						break;
				}
			}
		}

		countStep++;
	}

	@Override
	public void die() {
		Sound.zDie.play();
		this.active = false;
		drop = (int) (Math.random()*20);
		if(drop > 17)
			handler.getWorld().getItemManager().addItem(Item.upSpeed.createNew((int) x, (int) y));
		else if(drop > 14)
			handler.getWorld().getItemManager().addItem(Item.upPower.createNew((int) x, (int) y));
	}

}
