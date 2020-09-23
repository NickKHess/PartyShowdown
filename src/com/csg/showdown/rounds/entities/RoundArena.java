package com.csg.showdown.rounds.entities;

import java.util.List;
import org.bukkit.Location;

public class RoundArena {

	private String name;
	private List<Location> playerSpawns;

	public RoundArena(String name) {
		this.name = name;
	}

	public RoundArena() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Location> getPlayerSpawns() {
		return playerSpawns;
	}

	public void setPlayerSpawns(List<Location> spawns) {
		this.playerSpawns = spawns;
	}



}