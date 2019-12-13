package id.ac.its.kuuhakuCorporation.simpleRPG.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import id.ac.its.kuuhakuCorporation.simpleRPG.Handler;
import id.ac.its.kuuhakuCorporation.simpleRPG.entities.creatures.Player;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Assets;
import id.ac.its.kuuhakuCorporation.simpleRPG.gfx.Text;
import id.ac.its.kuuhakuCorporation.simpleRPG.items.Item;
import id.ac.its.kuuhakuCorporation.simpleRPG.utils.Sound;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	private int invX=64, invY=48, invWidth=512, invHeight=384, invListSpacing = 30,
			invListCenterX = invX + 171, invListCenterY = invY + invHeight/2 +5,
			invImageX = 452, invImageY = 82, invImageWidth =64, invImageHeight = 64,
			invCountX = 484, invCountY=172,
			selectedItem =0;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList();
	}
	
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
			Sound.inven.play();
		}
			
		if(!active)
			return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
			Sound.scroll.play();
			selectedItem--;
		}
			
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
			Sound.scroll.play();
			selectedItem++;
		}
			
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && inventoryItems.size() > 0) {
			Player player = handler.getWorld().getEntityManager().getPlayer();
			int health = player.getHealth();
			boolean poweredUp = player.getUpPower(),
					speededUp = player.getUpSpeed();
			
			if(health < 5 && inventoryItems.get(selectedItem).getTexture().equals(Assets.herb) ) {
				Sound.herb.play();
				player.setHealth(health + 1);
				reduceItem(selectedItem);
			}else if(inventoryItems.get(selectedItem).getTexture().equals(Assets.upSpeed) && !speededUp) {
				Sound.up.play();
				player.gettingUpSpeed();
				reduceItem(selectedItem);
			}else if(inventoryItems.get(selectedItem).getTexture().equals(Assets.upPower) && !poweredUp) {
				Sound.up.play();
				player.gettingUpPower();
				reduceItem(selectedItem);
			}else if(inventoryItems.get(selectedItem).getTexture().equals(Assets.rock)) {
				Sound.up.play();
				player.setDamage(player.getDamage() + 1);
				reduceItem(selectedItem);
			}
		}
		
		if(selectedItem <0)
			selectedItem = inventoryItems.size() -1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0; 
		
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		
		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight,null);
		
		int len = inventoryItems.size();
		if(len == 0)
			return;
		
		for(int i=-5; i<6; i++) {
			if(selectedItem+i<0 || selectedItem +i >= len)
				continue;
			if(i==0) 
				Text.drawString(g,"> "+ inventoryItems.get(selectedItem + i).getName() +" <",
						invListCenterX, invListCenterY + i*invListSpacing, true,
						Color.yellow, Assets.font28);	
			else
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(),
						invListCenterX, invListCenterY + i*invListSpacing, true,
						Color.white, Assets.font28);
			
		}
		
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight,null);
		Text.drawString(g, Integer.toString(item.getCount()),invCountX, invCountY,
				true, Color.white, Assets.font28); 
	}
	
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}
	
	public void reduceItem(int selectedItem) {
		if(inventoryItems.get(selectedItem).getCount() > 1) {
			int number = inventoryItems.get(selectedItem).getCount();
			inventoryItems.get(selectedItem).setCount(number - 1);
		}else {
			inventoryItems.remove(selectedItem);
		}
	}
}
