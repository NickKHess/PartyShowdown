package com.csg.utils;

import org.bukkit.plugin.java.JavaPlugin;

public final class CoinSlotUtils extends JavaPlugin {
	
	private static CoinSlotUtils plugin = null;
	
	@Override
	public void onEnable() {
		plugin = this;
	}

	@Override
	public void onDisable() {

	}

	public static CoinSlotUtils getPlugin() {
		return plugin;
	}

}