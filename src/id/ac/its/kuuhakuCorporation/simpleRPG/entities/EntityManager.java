 package id.ac.its.kuuhakuCorporation.simpleRPG.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Player;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;

	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity o1, Entity o2) {
			if(o1.getY()+o1.height < o2.getY()+o2.height)
				return -1;
			return 1;
		}
		
	};
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void tick() {
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()) {
			Entity e = it.next();
			e.tick();
			if(!e.isActive())
				it.remove();
		}
		
		entities.sort(renderSorter);
	}
	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
		
		player.postRender(g);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
}
