package com.csg.ware.events;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import com.csg.ware.Ware;

public class EventManager implements Listener {

	@EventHandler
	public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();
		UUID uuid = player.getUniqueId();
		if(Ware.getPlugin().getPlayers().contains(uuid))
			Ware.getPlugin().removePlayer(uuid);
	}
	
}