package com.csg.showdown.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import com.csg.showdown.Showdown;
import com.csg.showdown.rounds.rounds.RoundSnowballing;

public class RoundSnowballingEventManager implements Listener {

	RoundSnowballing snowballing;
	
	public RoundSnowballingEventManager() {
		this.snowballing = (RoundSnowballing) Showdown.getPlugin().getDirector().getCurrentGame();
	}
	
	@EventHandler
	public void onSnowballCollide(ProjectileHitEvent e) {
		if(e.getHitEntity() != null)
			if(e.getEntityType().equals(EntityType.SNOWBALL) &&
					e.getEntity().getShooter() instanceof Player &&
					e.getHitEntity() instanceof Player) {
				Snowball snowball = (Snowball) e.getEntity();

				Player shooter = (Player) snowball.getShooter();
				Player hit = (Player) e.getHitEntity();

			}
	}

}