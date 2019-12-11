package id.ac.its.kuuhakuCorporation.simpleRPG.worlds;

import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.EntityManager;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Player;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Zombie;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.statics.Rock;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.statics.Tree;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.items.ItemManager;
import id.ac.its.kuuhakuCorporation.simpleRPG.tiles.Tile;
import id.ac.its.kuuhakuCorporation.simpleRPG.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private int[][] entities;
	
	private EntityManager entityManager;
	private ItemManager itemManager;

	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler,new Player(handler, 100,100));
		itemManager = new ItemManager(handler);
		
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
		deployTree(20);
		
		deployRocks(20);
		
		deployZombie(10);
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		itemManager.render(g);
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void deployZombie(int numbers) {
		int i = 0;
		while(i < numbers) {
			int height = (int)(Math.random()*20);
			int width = (int)(Math.random()*20);
			if(!collisionWithTile(width,height)) {
				entityManager.addEntity(new Zombie(handler,width*64,height*64));
				entities[width][height] = 1;
				i++;
			}
		}
	}
	
	private void deployTree(int numbers) {
		int i = 0;
		while(i < numbers) {
			int height = (int)(Math.random()*20);
			int width = (int)(Math.random()*20);
			if(!collisionWithTile(width,height)) {
				entityManager.addEntity(new Tree(handler,width*64,height*64));
				entities[width][height] = 1;
				i++;
			}
		}
	}
	
	private void deployRocks(int numbers) {
		int i = 0;
		while(i < numbers) {
			int height = (int)(Math.random()*20);
			int width = (int)(Math.random()*20);
			if(!collisionWithTile(width,height)) {
				entityManager.addEntity(new Rock(handler,width*64,height*64));
				entities[width][height] = 1;
				i++;
			}
		}
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		entities = new int[width][height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				entities[x][y] = 0;
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return tiles[x][y] == 2 || entities[x][y] == 1;
	}
}
