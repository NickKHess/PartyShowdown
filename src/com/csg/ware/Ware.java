package com.csg.ware;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.csg.ware.commands.CommandWare;
import com.csg.ware.entities.GameDirector;
import com.csg.ware.rounds.generic.Round;
import com.csg.ware.rounds.rounds.RoundSnowballing;

public final class Ware extends JavaPlugin {
	
	private static Ware plugin = null;
	private GameDirector director = null;
	
	@Override
	public void onEnable() {
		plugin = this;
		director = new GameDirector();

		// Register general events
		Bukkit.getServer().getPluginManager().registerEvents(new GeneralEventManager(plugin), plugin);
		
		// Register commands
		plugin.getCommand("ware").setExecutor(new CommandWare());
		
		// Register games
		Round.games.add(new RoundSnowballing());
	}

	@Override
	public void onDisable() {

	}

	public static Ware getPlugin() {
		return plugin;
	}

	public GameDirector getDirector() {
		return director;
	}

	public void setDirector(GameDirector director) {
		this.director = director;
	}

}