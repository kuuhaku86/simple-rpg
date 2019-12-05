package id.ac.its.kuuhakuCorporation.simpleRPG.states;

import java.awt.Graphics;

import id.ac.its.kuuhakuCorporation.simpleRPG.Game;
import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.ClickListener;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.UIImageButton;
import id.ac.its.kuuhakuCorporation.simpleRPG.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(250, 150, 128, 64,
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
		uiManager.render(g);
	}

}
