package com.csg.utils.tasks;

import org.bukkit.scheduler.BukkitRunnable;

public class TimedBukkitRunnable extends BukkitRunnable {

	protected int time = 0;
	protected int limit;

	/***
	 * <i>TimedBukkitRunnable</i>
	 * <br>
	 * A BukkitRunnable that cancels itself after {@link limit} ticks
	 * 
	 * @param limit - The limit of time for which the runnable will run
	 */
	public TimedBukkitRunnable(int limit) {
		this.limit = limit;
	}

	@Override
	public void run() {
		if(time < limit)
			time++;
		else {
			this.cancel();
			return;
		}
	}

	public int getTime() {
		return time;
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
