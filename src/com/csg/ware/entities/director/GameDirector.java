package com.csg.ware.entities.director;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.csg.ware.Ware;
import com.csg.ware.entities.GamePlayer;
import com.csg.ware.rounds.generic.Round;

public final class GameDirector {

	private List<GamePlayer> players = new ArrayList<>();
	private Round currentRound;
	private int minPlayers;
	private int desiredPlayers;
	private int maxPlayers;

	private static GameDirector instance;

	public enum Phase {
		NONE, PREGAME, COUNTDOWN, INGAME, POSTGAME
	}
	
	/**
	 * <i>phase</i>
	 * The current phase of the game
	 */
	private Phase phase = Phase.NONE;
	private int round = 0;

	private CountdownRunnable countdown;

	/**
	 * <i>GameDirector</i>
	 * <br>
	 * The class that contains all logic and information for running
	 * minigames in real time
	 */
	public GameDirector(int minPlayers, int desiredPlayers, int maxPlayers) {
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
		// Populate players first because reasons
		populatePlayers();

		Random r = new Random();
		int i = r.nextInt(Round.availableRounds.size());
		Round round = Round.availableRounds.get(i);

		// Send each player the game information
		for(GamePlayer gPlayer : players) {
			Player player = Bukkit.getPlayer(gPlayer.getUUID());
			player.sendTitle(ChatColor.GREEN + "" + ChatColor.BOLD + round.getName(), 
					ChatColor.YELLOW + round.getDescription(), 10, 80, 10);
		}

		currentRound = round;
		currentRound.runTaskTimer(Ware.getPlugin(), 0, 1);
		return currentRound;
	}

	/**
	 * <i>stop</i>
	 * <br>
	 * Stops the game
	 */
	public void stop() {
		for(GamePlayer player : players)
			player.toPlayer().sendMessage(ChatColor.YELLOW + "The game has been" + ChatColor.RED + " STOPPED " + ChatColor.YELLOW + "by an admin or moderator.");
		players.clear();

		// Refresh the round instance from the list
		Round.availableRounds.remove(currentRound);
		try {
			Round.availableRounds.add(currentRound.getClass().newInstance());
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		currentRound.cancel();
		currentRound = null;
		phase = Phase.NONE;
		round = 0;
		instance = new GameDirector(minPlayers,desiredPlayers, maxPlayers);
	}

	public void startCountdown(int start) {
		setCountdown(new CountdownRunnable(this, start));
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

	public void populatePlayers() {
		players.clear();
		for(Player gamePlayer : Bukkit.getOnlinePlayers())
			addPlayer(gamePlayer.getUniqueId());		
	}

	public Round getCurrentGame() {
		return currentRound;
	}

	public void setCurrentGame(Round currentGame) {
		this.currentRound = currentGame;
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

	public int getDesiredPlayers() {
		return desiredPlayers;
	}
	
	public void setDesiredPlayers(int desiredPlayers) {
		this.desiredPlayers = desiredPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public CountdownRunnable getCountdown() {
		return countdown;
	}

	public void setCountdown(CountdownRunnable countdown) {
		this.countdown = countdown;
	}

}