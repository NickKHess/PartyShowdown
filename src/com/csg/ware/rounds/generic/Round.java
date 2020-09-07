package com.csg.ware.rounds.generic;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.csg.utils.tasks.TimedBukkitRunnable;
import com.csg.ware.Ware;
import com.csg.ware.entities.GamePlayer;
import com.csg.ware.entities.director.GameDirector;
import com.csg.ware.entities.director.GameDirector.Phase;

public abstract class Round extends TimedBukkitRunnable {

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
	private BossBar bossBar = Bukkit.createBossBar(ChatColor.LIGHT_PURPLE + "There are " + ChatColor.AQUA + ((int) (time / 20)) + ChatColor.LIGHT_PURPLE + " seconds left in the game!", BarColor.YELLOW, BarStyle.SOLID);

	private Listener listener = null;

	/**
	 * <i>Round</i>
	 * <br>
	 * An abstract class for minigame Rounds
	 * 
	 * @param name - The name of the game
	 * @param description - The description
	 * @param gameLength - The length of time, in seconds, which the game takes
	 * @param listener - The Event Manager to use
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
		switch(phase) {
		case NONE:
			break;
		case PREGAME:
			for(GamePlayer gamePlayer : director.getPlayers()) {

			}
			break;
		case INGAME:
			if(time == 0) {
				// Register events for the game
				Bukkit.getServer().getPluginManager().registerEvents(listener, Ware.getPlugin());

				startGame();
			}
			else if(time > 0 || time < limit) {
				gameLoop();

				for(GamePlayer gPlayer : director.getPlayers()) {
					Player player = Bukkit.getPlayer(gPlayer.getUUID());
					if(bossBar.getPlayers().contains(player))
						bossBar.addPlayer(player);
				}
			}
			else if(time == limit) {
				// Clean up
				endGame();
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