package id.ac.its.kuuhakuCorporation.simpleRPG.entities.statics;

import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.tiles.Tile;

public class Rock extends StaticEntity {

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 3;
		bounds.y = (int) (height/2f);
		bounds.width = width-6;
		bounds.height = (int) (height - height/1.5f);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int)(x- handler.getGameCamera().getxOffset()),(int)(y- handler.getGameCamera().getyOffset()), width,height,null);
		
	}
	
	
	
}
