package id.ac.its.kuuhakuCorporation.simpleRPG;

import id.ac.its.kuuhakuCorporation.simpleRPG.display.Display;

public class Game implements Runnable{
	private Display display;
	public int width,height;
	private boolean running = false;
	
	private Thread thread;
	
	public Game(String title,int height,int width) {
		this.width = width;
		this.height = height;
		
		display = new Display(title,width,height);
	}
	
	private void init() {
		
	}
	
	private void tick() {
		
	}
	
	private void render() {
		
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
