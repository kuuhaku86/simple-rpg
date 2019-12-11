package id.ac.its.kuuhakuCorporation.simpleRPG;

import id.ac.its.kuuhakuCorporation.simpleRPG.utils.Sound;

public class Launcher {
	public static void main(String[] args) {
		Sound.menu.loop();
		Game game = new Game("Game", 640, 470);
		game.start();
	}
}
