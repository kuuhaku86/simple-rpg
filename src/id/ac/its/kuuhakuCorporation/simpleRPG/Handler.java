package id.ac.its.kuuhakuCorporation.simpleRPG;

import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.GameCamera;
import id.ac.its.kuuhakuCorporation.simpleRPG.input.KeyManager;
import id.ac.its.kuuhakuCorporation.simpleRPG.input.MouseManager;
import id.ac.its.kuuhakuCorporation.simpleRPG.worlds.World;

public class Handler {

	private Game game;
	private World world;
	public int con;

	public Handler(Game game) {
		this.game = game;
		con=0;
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
