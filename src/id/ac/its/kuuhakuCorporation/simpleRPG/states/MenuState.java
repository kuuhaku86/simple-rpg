package id.ac.its.kuuhakuCorporation.simpleRPG.states;

import java.awt.Color;
import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Text;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.ClickListener;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.UIImageButton;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(250, 250, 128, 64,
				Assets.btn_start, new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null);
						State.setState(handler.getGame().gameState);
					}
		}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		Text.drawString(g, "SIMPLE RPG", 320, 150, true, Color.black, Assets.font28);
		Text.drawString(g, "Start?", 320, 200, true, Color.black, Assets.font28);
		uiManager.render(g);
	}
	
	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
}
