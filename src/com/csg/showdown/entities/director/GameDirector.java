package com.csg.showdown.entities.director;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import com.csg.showdown.Showdown;
import com.csg.showdown.entities.GamePlayer;
import com.csg.showdown.rounds.Round;
import com.csg.utils.player.PlayerCollisionToggler;

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
	private Phase phase = Phase.PREGAME;
	private int round = 0;

	private PregameRunnable countdown;

	/**
	 * <i>GameDirector</i>
	 * <br>
	 * The class that contains all logic and information for running
	 * minigames in real time
	 */
	public GameDirector(int minPlayers, int desiredPlayers, int maxPlayers) {
		this.minPlayers = minPlayers;
		this.desiredPlayers = desiredPlayers;
		this.maxPlayers = maxPlayers;

		instance = this;
	}

	/**
	 * <i>startRandomRound</i>
	 * <br>
	 * Selects a random {@link Round}, displays the round information to each player,
	 * then starts the round.
	 */
	public void startRandomRound() {
		phase = Phase.INGAME; // Set the GAME phase to ingame

		int i = new Random().nextInt(Round.availableRounds.size());
		Round selectedRound = Round.availableRounds.get(i);
		
		try {
			currentRound = selectedRound.getClass().newInstance();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		currentRound.setPhase(Phase.PREGAME); // Set the ROUND phase to pregame
		// Show game description + overview of level
		for(GamePlayer gPlayer : players) {
			Player player = gPlayer.toPlayer();

			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, .5f);
			player.sendTitle(ChatColor.GREEN + "" + ChatColor.BOLD + currentRound.getName(), 
					ChatColor.YELLOW + currentRound.getDescription(), 10, 80, 10);
			// TODO: Add overview
		}

		Bukkit.getScheduler().scheduleSyncDelayedTask(Showdown.getPlugin(), new Runnable() {
			@Override
			public void run() {
				currentRound.setPhase(Phase.INGAME); // Set the ROUND phase to pregame
				currentRound.runTaskTimer(Showdown.getPlugin(), 0, 1);
			}
		}, 10 + 80 + 10);
	}

	/**
	 * <i>stop</i>
	 * <br>
	 * Stops the current {@link Round}
	 */
	public void stop() {
		for(GamePlayer player : players)
			player.toPlayer().sendMessage(ChatColor.YELLOW + "The game has been" + ChatColor.RED + " STOPPED " + ChatColor.YELLOW + "by an admin or moderator.");
		players.clear();

		currentRound.cancel();
		currentRound = null;
		phase = Phase.NONE;
		round = 0;
		instance = new GameDirector(minPlayers, desiredPlayers, maxPlayers);
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

		if(!found) {
			players.add(new GamePlayer(uuid));
			PlayerCollisionToggler toggler = new PlayerCollisionToggler();
			toggler.addPlayer(Bukkit.getPlayer(uuid));
		}
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

	/**
	 * <i>startCountdown</i>
	 * <br>
	 * Starts a new CountdownRunnable
	 * @param start - the time, in seconds, to run the countdown for
	 */
	public void startCountdown(int start) {
		phase = Phase.COUNTDOWN;
		countdown = new PregameRunnable((start * 20) + 1);
		countdown.runTaskTimer(Showdown.getPlugin(), 0, 1);
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

	public PregameRunnable getCountdown() {
		return countdown;
	}

	public void setCountdown(PregameRunnable countdown) {
		this.countdown = countdown;
	}

}