package com.csg.ware.tasks.templates;

import org.bukkit.scheduler.BukkitRunnable;

public class LimitedBukkitRunnable extends BukkitRunnable {

<<<<<<< HEAD
<<<<<<< HEAD
	protected int time = 0;
	protected int limit;
=======
	private int time = 0;
	int stopAt = 20 + 80 + 20;
>>>>>>> parent of 0ea6496... Minor updates
=======
	private int time = 0;
	int stopAt = 20 + 80 + 20;
>>>>>>> 4ed8a936230415dd4244bfb2b407e83947c54033
	
	/***
	 * @param limit - The limit of time for which the runnable will run
	 */
<<<<<<< HEAD
	public LimitedBukkitRunnable(int limit) {
		this.limit = limit;
=======
	public LimitedBukkitRunnable(int length) {
		
<<<<<<< HEAD
>>>>>>> parent of 0ea6496... Minor updates
=======
>>>>>>> 4ed8a936230415dd4244bfb2b407e83947c54033
	}

	@Override
	public void run() {
<<<<<<< HEAD
<<<<<<< HEAD
		if(time < limit) {
=======
		if(time < stopAt) {
			
>>>>>>> parent of 0ea6496... Minor updates
=======
		if(time < stopAt) {
			
>>>>>>> 4ed8a936230415dd4244bfb2b407e83947c54033
			time++;
		}
	}
	
<<<<<<< HEAD
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
=======
	pu
>>>>>>> 4ed8a936230415dd4244bfb2b407e83947c54033

}
