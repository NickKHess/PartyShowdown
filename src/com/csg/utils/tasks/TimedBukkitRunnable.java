package com.csg.utils.tasks;

import org.bukkit.scheduler.BukkitRunnable;

public class TimedBukkitRunnable extends BukkitRunnable {

	protected int time = 0;
	protected int limit;
	protected boolean countDown;

	/***
	 * <i>LimitedBukkitRunnable</i>
	 * <br>
	 * A BukkitRunnable that cancels itself after {@link limit} ticks
	 * 
	 * @param limit - The limit of time for which the runnable will run
	 */
	public TimedBukkitRunnable(int limit) {
		this.limit = limit;
		this.countDown = false;
	}

	/***
	 * <i>LimitedBukkitRunnable</i>
	 * <br>
	 * A BukkitRunnable that cancels itself after {@link limit} ticks
	 * 
	 * @param limit - The limit of time for which the runnable will run
	 */
	public TimedBukkitRunnable(int limit, boolean countDown) {
		if(countDown) {
			this.time = limit;
			this.limit = 0;
		}
		else {
			this.time = 0;
			this.limit = limit;
		}
		this.countDown = countDown;
	}

	@Override
	public void run() {
		if(countDown) {
			if(time > limit)
				time--;
			else {
				this.cancel();
				return;
			}
		}
		else {
			if(time < limit)
				time++;
			else {
				this.cancel();
				return;
			}
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
