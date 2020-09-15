package com.csg.utils.tasks;

import org.bukkit.scheduler.BukkitRunnable;

public class CountdownRunnable extends BukkitRunnable {

	protected int start, time;

	/***
	 * <i>CountdownRunnable</i>
	 * <br>
	 * A {@link BukkitRunnable} that cancels itself after counting down from {@code start} to 0
	 * 
	 * @param start - The starting number of ticks
	 */
	public CountdownRunnable(int start) {
		this.start = start;
		this.time = start;
	}

	@Override
	public void run() {
		if(time > 0)
			time--;
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

	public int getStart() {
		return start;
	}

}
