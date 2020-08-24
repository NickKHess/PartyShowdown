package com.csg.ware.tasks;

import com.csg.ware.tasks.templates.LimitedBukkitRunnable;

public class TriggerRandomGame extends LimitedBukkitRunnable {

<<<<<<< HEAD
	//BossBar bar = Bukkit.getServer().createBossBar(this.time + " seconds left in " + GameMaster.getCurrentGame(), this.color, this.style, this.flag);
    //bar.setVisible(true);
    //bar.setProgress(this.progress);
	
	public TriggerRandomGame() {
		super(5 * 60 * 20);
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
=======
	
>>>>>>> parent of 0ea6496... Minor updates
	
}