package com.csg.ware.tasks.templates;

import org.bukkit.scheduler.BukkitRunnable;

public class LimitedBukkitRunnable extends BukkitRunnable {

<<<<<<< HEAD
	protected int time = 0;
	protected int limit;
=======
	private int time = 0;
	int stopAt = 20 + 80 + 20;
>>>>>>> parent of 0ea6496... Minor updates
	
	/***
	 * @param limit - The limit of time for which the runnable will run
	 */
<<<<<<< HEAD
	public LimitedBukkitRunnable(int limit) {
		this.limit = limit;
=======
	public LimitedBukkitRunnable(int length) {
		
>>>>>>> parent of 0ea6496... Minor updates
	}

	@Override
	public void run() {
<<<<<<< HEAD
		if(time < limit) {
=======
		if(time < stopAt) {
			
>>>>>>> parent of 0ea6496... Minor updates
			time++;
		}
	}
	
<<<<<<< HEAD
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
=======
	pu
>>>>>>> parent of 0ea6496... Minor updates

}
