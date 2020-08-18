package com.csg.ware.game;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

	public static List<Game> games = new ArrayList<Game>();
	
	private String name;
	private String description;
	
	public Game(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public abstract void performGameAction();
	
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