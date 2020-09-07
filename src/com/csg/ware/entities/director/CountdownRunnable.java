package com.csg.ware.entities.director;

import org.bukkit.ChatColor;
import com.csg.utils.tasks.TimedBukkitRunnable;
import com.csg.ware.entities.GamePlayer;
import com.csg.ware.entities.director.GameDirector.Phase;

public final class CountdownRunnable extends TimedBukkitRunnable {
	
	GameDirector director;
	
	public CountdownRunnable(GameDirector director, int start) {
		super(start, true);
		this.director = director;
	}
	
	@Override
	public void run() {
		ChatColor color = ChatColor.AQUA;
		switch(time) {
		case 5:
			color = ChatColor.GREEN;
			break;
		case 4:
			color = ChatColor.GOLD;
			break;
		case 3:
		case 2:
		case 1:
			color = ChatColor.RED;
			break;
		}
		
		if(time == 10 || time <= 5)
			for(GamePlayer gamePlayer : director.getPlayers())
				gamePlayer.toPlayer().sendMessage(ChatColor.YELLOW + "There are " + color + time + ChatColor.YELLOW + " seconds until the game starts!");
		
		if(time == limit)
			director.setPhase(Phase.INGAME);
	}

}