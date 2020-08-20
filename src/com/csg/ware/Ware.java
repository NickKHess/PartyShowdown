package com.csg.ware;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.csg.ware.commands.CommandWare;
import com.csg.ware.game.Game;
import com.csg.ware.game.games.GameSnowballFight;
import com.csg.ware.tasks.TriggerRandomGame;

public class Ware extends JavaPlugin {
	
	private static Ware plugin = null;
	public boolean started = false;
	
	private List<UUID> players = null;

	@Override
	public void onEnable() {
		plugin = this;
		players = new ArrayList<>();

		plugin.getCommand("ware").setExecutor(new CommandWare());
		
		Game.games.add(new GameSnowballFight());
	}

	@Override
	public void onDisable() {

	}
	
	public static void triggerRandomGame() {
		Random r = new Random();
		int i = r.nextInt(Game.games.size());
		Game selected = Game.games.get(i);
		
		// Send each player the game information
		for(Player player : plugin.getServer().getOnlinePlayers()) {
			player.sendTitle(ChatColor.GREEN + "" + ChatColor.BOLD + selected.getName(), 
					ChatColor.YELLOW + selected.getDescription(), 20, 80, 20);
		}
		// Wait before starting the game
		new TriggerRandomGame().runTaskTimer(plugin, 0, 1);
		
		// TODO: Make this a repeating task that counts down until the next game
		// After 5 minutes, trigger another random game
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			@Override
			public void run() {
				triggerRandomGame();
			}
			
		}, 1*60*20); // 2 minutes
	}

	public static Ware getPlugin() {
		return plugin;
	}

	public List<UUID> getPlayers() {
		return players;
	}
	
	public List<UUID> addPlayer(UUID uuid) {
		players.add(uuid);
		return players;
	}
	
	public List<UUID> removePlayer(UUID uuid) {
		players.remove(uuid);
		return players;
	}
	
	public void clearPlayers() {
		players.clear();
	}
	
}