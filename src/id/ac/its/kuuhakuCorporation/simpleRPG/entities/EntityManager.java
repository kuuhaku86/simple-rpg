package id.ac.its.kuuhakuCorporation.simpleRPG.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Player;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
	}
	
	public void tick() {
		for (int i=0;i<entities.size();i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		player.tick();
	}
	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
		player.render(g);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}

	private Handler getHandler() {
		return handler;
	}

	private void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}

	private ArrayList<Entity> getEntities() {
		return entities;
	}

	private void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
	
}
