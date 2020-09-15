package com.csg.utils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSignLocations implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("csa.signlocations")) {
				// 
				if(args.length >= 1) {
					// TODO: DO IDEAS IN COMMENTS
					// IMPLEMENT THEM IN SignLocationManager
					if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("create")) {
						// Instantiate a SignGroup
						// Add it to a list of SignGroup
						// SignGroup class - stores sign locations, exports to config
						// Begin editing the SignGroup (place Player and SignGroup in static HashMap??)
						// Should multiple people be able to edit?
					}
					else if(args[0].equalsIgnoreCase("edit") || args[0].equalsIgnoreCase("modify")) {
						// Check if SignGroup exists (by name)
						// Begin editing SignGroup
						// Should multiple people be able to edit?
					}
					else if(args[0].equalsIgnoreCase("confirm") || args[0].equalsIgnoreCase("finish")) {
						// Check if player is editing
						// Finalize everything, make sure it exports to config
						// Delete dem signs
						// Remove Player from HashMap
					}
					else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) {
						// Check if SignGroup exists
						// If editing, delete signs
						// Remove from config
						// ???
						// Profit
					}
				}
			}
			else
				player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
				
		}
		return true;
	}

}
