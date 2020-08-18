package com.csg.ware.tasks.templates;

import org.bukkit.scheduler.BukkitRunnable;

public class LimitedBukkitRunnable extends BukkitRunnable {

	private int time = 0;
	private int length = 0;
	
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
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
