package com.csg.ware.entities;

import java.util.UUID;

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