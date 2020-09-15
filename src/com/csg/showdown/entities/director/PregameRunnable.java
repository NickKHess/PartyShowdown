package com.csg.showdown.entities.director;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import com.csg.showdown.entities.GamePlayer;
import com.csg.utils.tasks.CountdownRunnable;

public class PregameRunnable extends CountdownRunnable {
	
	private GameDirector director;
	
	public PregameRunnable(int start) {
		super(start);
		this.director = GameDirector.instance();
	}
	
	@Override
	public void run() {
		ChatColor color = ChatColor.AQUA;
		switch(time) {
		case 5*20:
			color = ChatColor.GREEN;
			break;
		case 4*20:
			color = ChatColor.GOLD;
			break;
		case 3*20:
		case 2*20:
		case 1*20:
			color = ChatColor.RED;
			break;
		}
		
		if(time == 10*20 || (time <= 5*20 && time > 0 && time % 20 == 0))
			for(GamePlayer gamePlayer : director.getPlayers()) {
				Player player = gamePlayer.toPlayer();

				player.playSound(player.getLocation(), Sound.UI_STONECUTTER_SELECT_RECIPE, 1, 1);
				player.sendMessage(ChatColor.YELLOW + "There are " + color + time / 20 + ChatColor.YELLOW + " seconds until the game starts!");
			}
				
		if(time == 0) {
			director.startRandomRound();
			this.cancel();
		}
		
		// ALWAYS CALL SUPER
		super.run();
	}
	
	public GameDirector getDirector() {
		return director;
	}

}