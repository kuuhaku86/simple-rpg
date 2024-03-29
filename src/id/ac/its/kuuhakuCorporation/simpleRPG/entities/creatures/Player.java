package id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.Entity;
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
 private int powerUpDamage, ordinaryDamage;
 
 private boolean upPower,
     upSpeed;
 
 private long upSpeedTimer, upSpeedlimit = 10000, lastSpeedTimer;
 private long upPowerTimer, upPowerlimit = 10000, lastPowerTimer;
 private long hurtTimer,lastHurtTimer, hurtCooldown = 1000; 

 public Player(Handler handler, float x, float y) {
	 super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

	 bounds.x = 22;
	 bounds.y = 44;
	 bounds.width = 19;
	 bounds.height = 19;
  
	 upPower = false;
	 upSpeed = false;
  
	 health = 3;

	 animDown = new Animation(500, Assets.player_down);
	 animUp= new Animation(500, Assets.player_up);
	 animLeft = new Animation(500, Assets.player_left);
	 animRight = new Animation(500, Assets.player_right);

	 inventory = new Inventory(handler);
	 arrows = new ArrayList<Arrow>();
	 lastHurtTimer = System.currentTimeMillis();
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
  
	 if(upSpeed) {
		 attackCooldown = 200;
		 if((upSpeedTimer - lastSpeedTimer) > upSpeedlimit) {
			 upSpeed = false;
			 attackCooldown = 500;
			 lastSpeedTimer = System.currentTimeMillis();
		 }
		 upSpeedTimer = System.currentTimeMillis();
	 }
  
	 if(upPower) {
		 if((upPowerTimer - lastPowerTimer) > upPowerlimit) {
			 upPower = false;
			 lastPowerTimer = System.currentTimeMillis();
			 this.damage = ordinaryDamage;
		 }
		 this.damage = powerUpDamage;
		 upPowerTimer = System.currentTimeMillis();
	 }
  
	 checkAttacked();
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
 
 public void checkAttacked() {
	 for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
		 if(e instanceof Zombie && e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(bounds.x, bounds.y))) {
			 this.hurt(e.getDamage());
		 }
	 }
 }

 @Override
 public void die() {
	 handler.con=2;
	 handler.getGame().gameState.reset();
	 Sound.main.stop();
	 Sound.pHit.stop();
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
  
	 if(upPower) 
		 drawPowerUp(g);
	 if(upSpeed) 
		 drawSpeedUp(g);
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
 
 @Override
 public void hurt(int dmg) {
	 hurtTimer = System.currentTimeMillis() - lastHurtTimer;
	 if(hurtTimer < hurtCooldown)
		 return;
	 lastHurtTimer = System.currentTimeMillis();
	 health -= dmg;
	 Sound.pHit.play();
	 if(health<=0) {
		 active = false;
   
		 die();
	 }
 }

 public Inventory getInventory() {
	 return inventory;
 }

 public void setInventory(Inventory inventory) {
	 this.inventory = inventory;
 }
 
 public boolean getUpSpeed() {
	 return this.upSpeed;
 }
 
 public boolean getUpPower() {
	 return this.upPower;
 }
 
 public void gettingUpSpeed() {
	 upSpeed = true;
	 upSpeedTimer = lastSpeedTimer = System.currentTimeMillis(); 
 }
 
 public void gettingUpPower() {
	 upPower = true;
	 upPowerTimer = lastPowerTimer = System.currentTimeMillis(); 
	 ordinaryDamage = this.damage;
	 powerUpDamage = this.damage + 1;
 }
 
 public void drawPowerUp(Graphics g) {
	 g.drawImage(Assets.upPower,10,40, width/2, height/2, null);
 }
 
 public void drawSpeedUp(Graphics g) {
	 g.drawImage(Assets.upSpeed,10,74, width/2, height/2, null);
 }
}
