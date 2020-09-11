package com.csg.ware.entities.director;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import com.csg.utils.tasks.TimedBukkitRunnable;
import com.csg.ware.entities.GamePlayer;

public final class CountdownRunnable extends TimedBukkitRunnable {
	
	GameDirector director;
	
	public CountdownRunnable(GameDirector director, int start) {
		super(start, true);
		this.director = director;
	}
	
	@Override
	public void run() {
		// ALWAYS CALL SUPER
		super.run();
		
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
				
		if(time == limit)
			director.startRandomRound();
	}

}