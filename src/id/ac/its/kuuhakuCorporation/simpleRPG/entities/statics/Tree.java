package id.ac.its.kuuhakuCorporation.simpleRPG.entities.statics;

import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.items.Item;
import id.ac.its.kuuhakuCorporation.simpleRPG.tiles.Tile;

public class Tree extends StaticEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);
		health = 10;
		bounds.x = 10;
		bounds.y = (int) (height/1.5f);
		bounds.width = width-20;
		bounds.height = (int) (height - height/1.5f);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die() {
		drop = (int) (Math.random()*10);
		if(drop > 7)
			handler.getWorld().getItemManager().addItem(Item.herbItem.createNew((int) x, (int) y));
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int)(x- handler.getGameCamera().getxOffset()),(int)(y- handler.getGameCamera().getyOffset()), width,height,null);
	}
	
	
	
}
