package com.csg.ware;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.csg.ware.entities.director.CountdownRunnable;
import com.csg.ware.entities.director.GameDirector;
import com.csg.ware.entities.director.GameDirector.Phase;

// All events in this class should have HIGHEST priority
// This will make sure they are called first, as they are the most important
public final class GeneralEventManager implements Listener {

	final int TIME_MIN_PLAYERS = 60;
	final int TIME_DESIRED_PLAYERS = 30;
	final int TIME_MAX_PLAYERS = 10;

	Ware plugin = null;
	GameDirector director = null;
	CountdownRunnable countdown = null;

	public GeneralEventManager(Ware plugin) {
		this.plugin = plugin;
		this.director = plugin.getDirector();

		//director.populatePlayers();
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		int players = director.getPlayers().size();

		switch(director.getPhase()) {
		case NONE:
			break;
		case COUNTDOWN:
			// If we drop below the threshold, move back a phase
			if(director.getPlayers().size() < director.getMinPlayers()) {
				director.setPhase(Phase.PREGAME);
			}
			
			// If we reach desired players or max players, reconfigure the time
			if(players == director.getDesiredPlayers() && director.getCountdown().getTime() > TIME_DESIRED_PLAYERS) {
				director.getCountdown().setTime(TIME_DESIRED_PLAYERS);
			}
			else if(players == director.getMaxPlayers() && director.getCountdown().getTime() > TIME_MAX_PLAYERS) {
				director.getCountdown().setTime(TIME_MAX_PLAYERS);
			}
		case PREGAME:
			director.addPlayer(e.getPlayer().getUniqueId());

			// If there are more players than we need to start
			if(players == director.getMinPlayers() && director.getCountdown().getTime() > TIME_MIN_PLAYERS) {
				director.setPhase(Phase.COUNTDOWN);
				director.startCountdown(TIME_MIN_PLAYERS);
			}
			break;
		case INGAME:
		case POSTGAME:
			// TODO: Add spectators
			// director.addSpectator(e.getPlayer().getUniqueId());
			break;
		}
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		switch(director.getPhase()) {
		case NONE:
			break;
		case COUNTDOWN:
			if(director.getPlayers().size() < director.getMinPlayers()) {
				director.setPhase(Phase.PREGAME);
			}
		case PREGAME:
			director.removePlayer(e.getPlayer().getUniqueId());
			break;
		case INGAME:
			break;
		case POSTGAME:
			break;
		}


	}

}