package id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Animation;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.inventory.Inventory;
import id.ac.its.kuuhakuCorporation.simpleRPG.states.State;
import id.ac.its.kuuhakuCorporation.simpleRPG.utils.Sound;

public class Player extends Creature {

	private Animation animDown,animUp,animLeft,animRight;
	private static long  attackCooldown = 500;
	private long lastAttackTimer, attackTimer = attackCooldown;
	private Inventory inventory;
	private ArrayList<Arrow> arrows;

	

	public static void setAttackCooldown() {
		Player.attackCooldown -= 200;
	}

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;

		health = 3;

		animDown = new Animation(500, Assets.player_down);
		animUp= new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);

		inventory = new Inventory(handler);
		arrows = new ArrayList<Arrow>();
	}

	@Override
	public void tick() {

		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		getInput();
		move();
		for (Arrow arrow : arrows) {
			arrow.tick();
		}
		handler.getGameCamera().certerOnEntity(this);
		checkAttacks();
		inventory.tick();
	}

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;

		if(inventory.isActive())
			return;

		if(handler.getKeyManager().aUp) {
			arrows.add(new Arrow(handler, this.x,this.y, Assets.arrow_up));
		}
		else if(handler.getKeyManager().aDown) {
			arrows.add(new Arrow(handler, this.x, this.y, Assets.arrow_down));
		}
		else if(handler.getKeyManager().aLeft) {
			arrows.add(new Arrow(handler, this.x, this.y, Assets.arrow_left));
		}
		else if(handler.getKeyManager().aRight) {
			arrows.add(new Arrow(handler, this.x, this.y, Assets.arrow_right));
		}
		else
			return;

		attackTimer = 0;
	}

	@Override
	public void die() {
		handler.con=2;
		handler.getGame().gameState.reset();
		Sound.main.stop();
		Sound.lose.play();
		State.setState(handler.getGame().menuState);
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
		for (Arrow arrow : arrows) {
			arrow.render(g);
		}

		g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		drawHeart(g);
	}

	public void postRender(Graphics g) {
		inventory.render(g);
	}

	private void drawHeart(Graphics g) {
		for(int i = 0; i < health;i++) {
			g.drawImage(Assets.heart,10 + 32 * i,10, width/2, height/2, null);
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

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
