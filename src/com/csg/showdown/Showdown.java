package com.csg.showdown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.csg.showdown.commands.CommandShowdown;
import com.csg.showdown.entities.director.GameDirector;
import com.csg.showdown.events.GeneralEventManager;
import com.csg.showdown.rounds.rounds.Round;
import com.csg.showdown.rounds.rounds.RoundSnowballing;
import com.csg.utils.commands.CommandSpawn;

public final class Showdown extends JavaPlugin {
	
	private static Showdown plugin = null;
	private GameDirector director = null;
	
	@Override
	public void onEnable() {
		plugin = this;
		director = new GameDirector(8, 12, 16);
		
		// Register general events
		Bukkit.getServer().getPluginManager().registerEvents(new GeneralEventManager(plugin), plugin);

		// Register commands
		plugin.getCommand("showdown").setExecutor(new CommandShowdown());
		plugin.getCommand("spawn").setExecutor(new CommandSpawn());
		
		// Register games
		Round.availableRounds.add(new RoundSnowballing());
	}

	@Override
	public void onDisable() {
		// Nothing yet
	}

	public static Showdown getPlugin() {
		return plugin;
	}

	public GameDirector getDirector() {
		return director;
	}

	public void setDirector(GameDirector director) {
		this.director = director;
	}

}