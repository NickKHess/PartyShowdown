package com.csg.ware.entities;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class GamePlayer {

	private UUID uuid;
	
	private boolean participating;
	private int score;
	
	public GamePlayer(UUID uuid) {
		this.uuid = uuid;
		participating = true;
		score = 0;
	}
	
	public GamePlayer(UUID uuid, boolean participating) {
		this.uuid = uuid;
		this.participating = participating;
		score = 0;
	}
	
	public Player toPlayer() {
		return Bukkit.getPlayer(uuid);
	}
	
	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isParticipating() {
		return participating;
	}

	public void setParticipating(boolean participating) {
		this.participating = participating;
	}

}