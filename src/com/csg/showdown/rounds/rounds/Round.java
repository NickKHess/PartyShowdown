package com.csg.showdown.rounds.rounds;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.csg.showdown.Showdown;
import com.csg.showdown.entities.GamePlayer;
import com.csg.showdown.entities.director.GameDirector;
import com.csg.showdown.entities.director.GameDirector.Phase;
import com.csg.utils.tasks.CountdownRunnable;

public abstract class Round extends CountdownRunnable {

	public static List<Round> availableRounds = new ArrayList<>();

	private String name = "Round";
	private String description = "Just implement a round 4Head";

	/**
	 * <i>phase</i>
	 * <br>
	 * The current phase of the round
	 */
	private Phase phase = Phase.NONE;
	/**
	 * <i>gameLength</i>
	 * <br>
	 * The length of the game in seconds
	 */
	private int gameLength = 300;
	private BossBar bossBar = Bukkit.createBossBar(ChatColor.LIGHT_PURPLE + "There are " + ChatColor.AQUA + ((int) (time / 20)) + ChatColor.LIGHT_PURPLE + " seconds left!", BarColor.BLUE, BarStyle.SOLID);

	private Listener listener = null;

	/**
	 * <i>Round</i>
	 * <br>
	 * An abstract class for minigame Rounds
	 * 
	 * @param name - The name of the game
	 * @param description - The description
	 * @param gameLength - The length of time, in seconds, which the game takes
	 * @param listener - The {@link Listener} (event manager) to use
	 */
	public Round(String name, String description, int gameLength, Listener listener) {
		super((gameLength) * 20);

		this.name = name;
		this.description = description;
		this.gameLength = gameLength;
		this.listener = listener;
	}

	@Override
	public void run() {
		GameDirector director = GameDirector.instance();
		
		int seconds = time / 20;
		
		switch(phase) {
		case NONE:
		case PREGAME:
			// Pregame handled in GameDirector.startRandomRound();
			break;
		case INGAME:
			if(seconds == gameLength) {
				// Register events for the game
				Bukkit.getServer().getPluginManager().registerEvents(listener, Showdown.getPlugin());

				// Show all players the boss bar
				for(GamePlayer gPlayer : director.getPlayers()) {
					Player player = Bukkit.getPlayer(gPlayer.getUUID());
					if(!bossBar.getPlayers().contains(player))
						bossBar.addPlayer(player);
				}
				
				startGame();
			}
			else if(seconds > 0 || seconds < start * 20) {
				bossBar.setTitle(ChatColor.LIGHT_PURPLE + "There are " + ChatColor.AQUA + ((int) (time / 20)) + ChatColor.LIGHT_PURPLE + " seconds left!");
				gameLoop();
			}
			else if(seconds == 0) {
				// Clean up
				endGame();
				// TODO: Doesn't actually WORK
				bossBar.removeAll();

				time = 0;
				phase = Phase.POSTGAME;
			}
			break;
		case POSTGAME:
			postGame();
			// TODO: Leaderboard and scores
			break;
		default:
			break;
		}
		
		// ALWAYS CALL SUPER
		super.run();
	}

	public abstract void startGame();
	public abstract void gameLoop();
	public abstract void endGame();
	public abstract void postGame();

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

	public int getGameLength() {
		return gameLength;
	}

	public void setGameLength(int gameLength) {
		this.gameLength = gameLength;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

}