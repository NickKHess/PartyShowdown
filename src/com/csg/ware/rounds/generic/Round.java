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
import com.csg.ware.Ware;
import com.csg.ware.entities.GameDirector;
import com.csg.ware.entities.GamePlayer;
import com.csg.ware.tasks.LimitedBukkitRunnable;

public abstract class Round extends LimitedBukkitRunnable {

	public static List<Round> games = new ArrayList<Round>();

	private String name = "Game";
	private String description = "Just implement a game 4Head";

	/**
	 * <i>phase</i>
	 * The current phase of the game
	 * 
	 * 0 = None
	 * 1 = Pregame
	 * 2 = Game
	 * 3 = Postgame
	 */
	private int phase = 0;
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
		case 1:
			// Pregame
			int currentTimeLimit = 300 * 20;
			while(time < currentTimeLimit) {
				// Game is full
				if(director.getPlayers().size() == director.getMaxPlayers())
					currentTimeLimit = 10 * 20;
				// Game is filled enough to play, but not quite yet
				else if(director.getPlayers().size() > director.getMinPlayers() && time < 240)
					currentTimeLimit = 60 * 20;

				ChatColor color = ChatColor.AQUA;

				int timeLeft = (currentTimeLimit - time) / 20;
				switch(timeLeft) {
				case 10:
				case 9:
				case 8:
				case 7:
				case 6:
					color = ChatColor.GREEN;
				case 5:
				case 4:
					color = ChatColor.YELLOW;
				case 3:
				case 2:
					color = ChatColor.GOLD;
				case 1:
					color = ChatColor.RED;
					for(GamePlayer gPlayer : director.getPlayers()) {
						Bukkit.getPlayer(gPlayer.getUUID()).sendMessage(ChatColor.YELLOW + "The game will start in " + color + timeLeft + ChatColor.YELLOW + " seconds!");
						// TODO: Add FeatherBoard Support
					}
					break;
				}
			}
			if(time == 15 * 20) {
				time = 0;
				phase = 1;
			}
		case 2:
			// Start and end game
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
				phase = 2;
			}
		case 3:
			// Postgame
			postGame();
			// TODO: Leaderboard and scores
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

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

}