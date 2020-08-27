package com.csg.ware.tasks;

import org.bukkit.scheduler.BukkitRunnable;

public class LimitedBukkitRunnable extends BukkitRunnable {

	protected int time = 0;
	protected int limit;
	
	/***
	 * <i>LimitedBukkitRunnable</i>
	 * <br>
	 * A BukkitRunnable that cancels itself after {@link limit} ticks
	 * 
	 * @param limit - The limit of time for which the runnable will run
	 */
	public LimitedBukkitRunnable(int limit) {
		this.limit = limit;
	}

	@Override
	public void run() {
		if(time < limit) {
			time++;
		}
		else
			this.cancel();
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
