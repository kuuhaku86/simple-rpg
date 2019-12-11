package id.ac.its.kuuhakuCorporation.simpleRPG.entities.statics;

import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.items.Item;
import id.ac.its.kuuhakuCorporation.simpleRPG.tiles.Tile;
import id.ac.its.kuuhakuCorporation.simpleRPG.utils.Sound;

public class Bush extends StaticEntity {

	public Bush(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		health = 4;
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 1.5f);
	}

	@Override
	public void tick() {

	}

	@Override
	public void die() {
		Sound.leaf.play();
		drop = (int) (Math.random()*10);
		if(drop > 7)
			handler.getWorld().getItemManager().addItem(Item.herbItem.createNew((int) x, (int) y));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bush, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

	}

}
