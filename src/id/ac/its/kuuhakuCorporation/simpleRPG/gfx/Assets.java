package id.ac.its.kuuhakuCorporation.simpleRPG.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	
	public static BufferedImage  dirt, grass, stone, tree,bush,wood;
	public static BufferedImage[] player_down,
									player_up,
									player_left,
									player_right,
									zombie_down,
									zombie_up,
									zombie_left,
									zombie_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;
	public static BufferedImage arrow_up,arrow_down,arrow_left,arrow_right;
	public static BufferedImage heart;
	public static BufferedImage title;
	public static BufferedImage health_bar_full,
									health_bar_23,
									health_bar_13;
	
	public static void init() {
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		title = ImageLoader.loadImage("/textures/title.png");
		btn_start= new BufferedImage[2];
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		btn_start[0] = sheet.crop(width*6,  height*4, width*2, height);
		btn_start[1] = sheet.crop(width*6,  height*5, width*2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		 
		player_down[0]= sheet.crop(width * 4,0,width,height);
		player_down[1]= sheet.crop(width * 5,0,width,height); 
		player_up[0]= sheet.crop(width * 6,0,width,height);
		player_up[1]= sheet.crop(width * 7,0,width,height);
		player_left[0]= sheet.crop(width * 6,height,width,height);
		player_left[1]= sheet.crop(width * 7,height,width,height);
		player_right[0]= sheet.crop(width * 4,height,width,height);
		player_right[1]= sheet.crop(width * 5,height,width,height);
		
		zombie_down[0]= sheet.crop(width * 4,2*height,width,height);
		zombie_down[1]= sheet.crop(width * 5,2*height,width,height); 
		zombie_up[0]= sheet.crop(width * 6,2*height,width,height);
		zombie_up[1]= sheet.crop(width * 7,2*height,width,height);
		zombie_left[0]= sheet.crop(width * 6,3*height,width,height);
		zombie_left[1]= sheet.crop(width * 7,3*height,width,height);
		zombie_right[0]= sheet.crop(width * 4,3*height,width,height);
		zombie_right[1]= sheet.crop(width * 5,3*height,width,height);
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height*2);
		bush = sheet.crop(0, 0, width, height);
		wood = sheet.crop(width, height, width, height);
		
		arrow_up = sheet.crop(width, height*3, width, height);
		arrow_down = sheet.crop(0, height*3, width, height);
		arrow_left = sheet.crop(3*width, height*3, width, height);
		arrow_right = sheet.crop(2*width, height*3, width, height);
		
		heart = sheet.crop(3*width, 2*height, width, height);
		
		health_bar_full = sheet.crop(3*width, 4*height, width, height);
		health_bar_23 = sheet.crop(2*width, 4*height, width, height);
		health_bar_13 = sheet.crop(1*width, 4*height, width, height);
	}
}
