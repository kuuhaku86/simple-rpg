package id.ac.its.kuuhakuCorporation.simpleRPG.entities.statics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	protected int drop;
	
	public StaticEntity(Handler handler, float x, float y, int width, int height ) {
		super(handler,x,y,width,height);
	}
	
	
	
}
