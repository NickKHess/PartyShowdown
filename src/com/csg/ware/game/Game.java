package com.csg.ware.game;

import java.util.ArrayList;
import java.util.List;
import com.csg.ware.tasks.templates.LimitedBukkitRunnable;

public abstract class Game extends LimitedBukkitRunnable {

	public static List<Game> games = new ArrayList<Game>();
	
	private String name;
	private String description;
	
	/**
	 * @param name - The name of the game
	 * @param description - The description
	 * @param length - The length of time, in minutes, which the game takes
	 */
	public Game(String name, String description, float length) {
		super((int) (length * 60 * 20));
		this.name = name;
		this.description = description;
	}
	
	@Override
	public void run() {
		if(time == 0)
			startGame();
		else if(time > 0 || time < 0)
			gameTask();
		else if(time == limit)
			endGame();
	}

	public abstract void startGame();
	public abstract void gameTask();
	public abstract void endGame();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}