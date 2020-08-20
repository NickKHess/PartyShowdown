package com.csg.ware.tasks.templates;

import org.bukkit.scheduler.BukkitRunnable;

public class LimitedBukkitRunnable extends BukkitRunnable {

	private int time = 0;
	int stopAt = 20 + 80 + 20;
	
	/***
	 * @param length - The length of time before the timer stops
	 */
	public LimitedBukkitRunnable(int length) {
		
	}

	@Override
	public void run() {
		if(time < stopAt) {
			
			time++;
		}
	}
	
	pu

}
