package com.csg.ware.tasks.templates;

import org.bukkit.scheduler.BukkitRunnable;

public class LimitedBukkitRunnable extends BukkitRunnable {

	protected int time = 0;
	int length;
	
	/***
	 * @param length - The length of time before the timer stops
	 */
	public LimitedBukkitRunnable(int length) {
		this.length = length;
	}

	@Override
	public void run() {
		if(time < length) {
			time++;
		}
		else {
			this.cancel();
		}
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

}
