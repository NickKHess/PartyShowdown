package com.csg.ware.tasks;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.csg.ware.Ware;
import com.csg.ware.tasks.templates.LimitedBukkitRunnable;

public class TriggerRandomGame extends LimitedBukkitRunnable {

	public TriggerRandomGame() {
		super(20 + 80 + 20 + (2 * 60 * 20)); // Enough time to show the title + a few minutes
	}

	@Override
	public void run() {
		// Do nothing when time is less than 120, then...
		if(this.time > 120) {
			if((this.time - 120) % 60 % 20 == 0) {
				for(UUID uuid : Ware.getPlugin().getPlayers()) {
					Player player = Bukkit.getPlayer(uuid);
					
					
				}
					
			}
		}
			
	}
	
}