package com.csg.ware;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.csg.ware.entities.GameDirector;
import com.csg.ware.entities.GamePlayer;

// All events in this class should have HIGHEST priority
// This will make sure they are called first, as they are the most important
public final class GeneralEventManager implements Listener {

	Ware plugin = null;
	GameDirector director;
	
	public GeneralEventManager(Ware plugin) {
		this.plugin = plugin;
		this.director = plugin.getDirector();
		
		for(GamePlayer gamePlayer : director.getPlayers())
			director.addPlayer(gamePlayer.getUUID());
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		director.addPlayer(e.getPlayer().getUniqueId());
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		director.removePlayer(e.getPlayer().getUniqueId());
	}
	
}