package com.csg.showdown.rounds.entities;

import java.util.List;
import org.bukkit.Location;

public class RoundArenaBuilder {

	private RoundArena roundArena = new RoundArena();
	
	public RoundArenaBuilder setName(String name) {
		roundArena.setName(name);
		return this;
	}
	
	public RoundArenaBuilder addSpawn(Location spawn) {
		List<Location> spawns = roundArena.getPlayerSpawns();
		spawns.add(spawn);
		roundArena.setPlayerSpawns(spawns);
		return this;
	}
	
	public RoundArena build() {
		return roundArena;
	}
	
}