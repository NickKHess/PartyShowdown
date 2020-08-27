package com.csg.ware.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.csg.ware.Ware;
import com.csg.ware.rounds.generic.Round;

public final class GameDirector {

	private List<GamePlayer> players = new ArrayList<>();
	private Round currentGame;
	private int minPlayers;
	private int maxPlayers;

	private static GameDirector instance;

	private int phase = 0;
	private int round = 0;

	/**
	 * <i>GameDirector</i>
	 * <br>
	 * The class that contains all logic and information for running
	 * minigames in real time
	 */
	public GameDirector() {
		instance = this;
	}

	/**
	 * <i>startRandomGame</i>
	 * <br>
	 * Selects a random {@link Round}, displays the game information to each player,
	 * then starts the game.
	 * @returns game - The selected game
	 */
	public Round startRandomGame() {
		Random r = new Random();
		int i = r.nextInt(Round.games.size());
		Round game = Round.games.get(i);

		// Send each player the game information
		for(GamePlayer gPlayer : players) {
			Player player = Bukkit.getPlayer(gPlayer.getUUID());
			player.sendTitle(ChatColor.GREEN + "" + ChatColor.BOLD + game.getName(), 
					ChatColor.YELLOW + game.getDescription(), 10, 80, 10);
		}

		game.runTaskTimer(Ware.getPlugin(), 0, 1);
		currentGame = game;
		return game;
	}

	public List<GamePlayer> getPlayers() {
		return players;
	}

	/**
	 * <i>addPlayer</i>
	 * <br>
	 * Checks {@link players} for the given player, then adds it if it's not there
	 * 
	 * @param uuid - The UUID of the player to be added
	 */
	public void addPlayer(UUID uuid) {
		boolean found = false;
		for(GamePlayer gPlayer : players)
			if(gPlayer.getUUID().equals(uuid))
				found = true;
		
		if(!found)
			players.add(new GamePlayer(uuid));
	}

	/**
	 * <i>removePlayer</i>
	 * <br>
	 * Checks {@link players} for the given player, then removes it if it's there
	 * 
	 * @param uuid - The UUID of the player to be removed
	 */
	public void removePlayer(UUID uuid) {
		for(GamePlayer gPlayer : players)
			if(gPlayer.getUUID().equals(uuid))
				players.remove(gPlayer);
	}

	public Round getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(Round currentGame) {
		this.currentGame = currentGame;
	}

	public static GameDirector instance() {
		return instance;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

}