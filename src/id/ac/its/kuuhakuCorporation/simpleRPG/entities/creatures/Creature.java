package id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures;

import id.ac.its.kuuhakuCorporation.simpleRPG.entities.Entity;

public abstract class Creature extends Entity {

	protected int health;
	
	public Creature(float x, float y) {
		super(x, y);
		health = 10;
	}
	

}
