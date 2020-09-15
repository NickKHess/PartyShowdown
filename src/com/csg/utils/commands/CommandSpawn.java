package com.csg.utils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("csa.spawn")) {
				player.teleport(player.getWorld().getSpawnLocation());
				player.sendMessage(ChatColor.YELLOW + "Teleporting to spawn...");
			}
			else
				player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
		}
		return true;
	}

}