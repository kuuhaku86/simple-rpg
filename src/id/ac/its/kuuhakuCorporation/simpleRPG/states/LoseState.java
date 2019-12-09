package id.ac.its.kuuhakuCorporation.simpleRPG.states;

import java.awt.Color;
import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Text;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.ClickListener;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.UIImageButton;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.UIManager;


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
