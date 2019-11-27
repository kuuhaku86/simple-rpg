package id.ac.its.kuuhakuCorporation.simpleRPG;

import id.ac.its.kuuhakuCorporation.simpleRPG.display.Display;

public class Game {
	private Display display;
	
	public int width,height;
	
	public Game(String title,int height,int width) {
		this.width = width;
		this.height = height;
		
		display = new Display(title,width,height);
	}
}
