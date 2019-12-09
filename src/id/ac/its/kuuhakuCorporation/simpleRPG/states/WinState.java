package id.ac.its.kuuhakuCorporation.simpleRPG.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Text;

public class WinState extends State {

	public WinState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		Text.drawString(g, "You Win", 320, 200, true, Color.black, Assets.font28);

	}

}
