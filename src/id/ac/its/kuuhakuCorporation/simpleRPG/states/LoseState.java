package id.ac.its.kuuhakuCorporation.simpleRPG.states;

import java.awt.Color;
import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Text;


public class LoseState extends State {
	
	public LoseState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		Text.drawString(g, "You Lose", 320, 200, true, Color.black, Assets.font28);
	}
}
