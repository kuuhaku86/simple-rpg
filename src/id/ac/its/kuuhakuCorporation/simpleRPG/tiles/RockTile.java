package id.ac.its.kuuhakuCorporation.simpleRPG.tiles;

import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
