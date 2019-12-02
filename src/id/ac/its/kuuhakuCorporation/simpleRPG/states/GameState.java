package id.ac.its.kuuhakuCorporation.simpleRPG.states;

import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Game;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Player;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;

public class GameState extends State{
	
	private Player player;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
	}
	
	@Override
	public void tick() {
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
	}

}
