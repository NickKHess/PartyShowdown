package com.csg.ware;

import java.util.Random;
<<<<<<< HEAD
import java.util.UUID;
=======

import org.bukkit.Bukkit;
>>>>>>> parent of 0ea6496... Minor updates
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.csg.ware.commands.CommandWare;
import com.csg.ware.game.Game;
import com.csg.ware.game.games.GameDropInventory;
import com.csg.ware.game.games.GameRandomTeleport;

public class Ware extends JavaPlugin {
	
	private static Ware plugin = null;
	public boolean started = false;

	@Override
	public void onEnable() {
		plugin = this;

		plugin.getCommand("ware").setExecutor(new CommandWare());
		
		Game.games.add(new GameRandomTeleport());
		Game.games.add(new GameDropInventory());
	}

	@Override
	public void onDisable() {

	}

	public static Ware getPlugin() {
		return plugin;
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
<<<<<<< HEAD
		new TriggerRandomGame().runTaskTimer(plugin, 0, 1);
=======
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				triggerRandomGame();
			}
			
		}, ); // Enough time for the title to show
		selected.performGameAction();
		
		// TODO: Make this a repeating task that counts down until the next game
		// After 5 minutes, trigger another random game
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			@Override
			public void run() {
				triggerRandomGame();
			}
			
		}, 1*60*20); // 2 minutes
>>>>>>> parent of 0ea6496... Minor updates
	}
	
}