package id.ac.its.kuuhakuCorporation.simpleRPG;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import id.ac.its.kuuhakuCorporation.simpleRPG.display.Display;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.ImageLoader;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.SpriteSheet;

public class Game implements Runnable{
	private Display display;
	public String title;
	public int width,height;
	
	private boolean running = false;	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public Game(String title,int width,int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	private void init() {
		display = new Display(title,width,height);
		Assets.init();
	}
	
	private void tick() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here
		
		g.drawImage(Assets.grass, 10, 10, null);
		
		// End Drawing
		bs.show();
		g.dispose();
	}
	
	public void run() {
		 init();
		 
		 while(running) {
			 tick();
			 render();
		 }
		 
		 stop();
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
