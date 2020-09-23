package com.csg.showdown.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import com.csg.showdown.entities.director.GameDirector;
import com.csg.showdown.entities.director.GameDirector.Phase;
import com.csg.showdown.rounds.entities.RoundArenaBuilder;
import net.md_5.bungee.api.ChatColor;

public final class CommandShowdown implements CommandExecutor, TabCompleter {

	Map<UUID, RoundArenaBuilder> editing = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {
				GameDirector director = GameDirector.instance();
				if(player.hasPermission("showdown.admin")) {
					if(args[0].equalsIgnoreCase("start")) {
						if(director.getCurrentGame() == null) {
							if(director.getPhase().equals(Phase.PREGAME)) {
								director.populatePlayers();
								director.startCountdown(10);
							}
							else
								player.sendMessage(ChatColor.RED + "This command can only be executed during PREGAME!");
						}
						else
							player.sendMessage(ChatColor.RED + "The game is already running!");
					}
					else if(args[0].equalsIgnoreCase("stop")) {
						if(director.getCurrentGame() != null)
							director.stop();
						else
							player.sendMessage(ChatColor.RED + "There is no game running!");
					}
					else
						player.sendMessage(ChatColor.RED + "Invalid usage!");
				}
				else
					player.sendMessage(ChatColor.RED + "You do not have permission to send that command!");
			}
			else if(args.length >= 2) {
				if(player.hasPermission("showdown.arena")) {
					if(args[0].equalsIgnoreCase("arena")) {
						if(args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("create")) {
							if(!editing.containsKey(player.getUniqueId())) {
								if(args.length == 3)
									editing.put(player.getUniqueId(), new RoundArenaBuilder().setName(args[2]));
								else
									editing.put(player.getUniqueId(), new RoundArenaBuilder());

								player.sendMessage(ChatColor.YELLOW + "");
							}
							else
								player.sendMessage(ChatColor.RED + "You're already editing a map! To stop, do /showdown arena cancel or /showdown arena save");
							// Instantiate an Arena
							// Add it to a list of Arenas
							// SignGroup class - stores sign locations, exports to config
							// Begin editing the SignGroup (place Player and Arena in static HashMap??)
							// Should multiple people be able to edit?
						}
						else if(args[1].equalsIgnoreCase("rename")) {
							if(editing.containsKey(player.getUniqueId())) {

							}
						}
						else if(args[1].equalsIgnoreCase("edit") || args[1].equalsIgnoreCase("modify")) {
							if(!editing.containsKey(player.getUniqueId())) {

							}
							// Check if Arena exists (by name)
							// Begin editing Arena
							// Should multiple people be able to edit?
						}
						else if(args[1].equalsIgnoreCase("confirm") || args[1].equalsIgnoreCase("finish")) {

							// Check if player is editing an Arena
							// Finalize everything, make sure it exports to config
							// Delete dem signs
							// Remove Player from HashMap
						}
						else if(args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("delete")) {
							// Check if Arena exists
							// If editing, delete signs
							// Remove from config
							// ???
							// Profit
						}
						else
							player.sendMessage(ChatColor.RED + "Invalid usage!");
					}
					else
						player.sendMessage(ChatColor.RED + "Invalid usage!");
				}
				else
					player.sendMessage(ChatColor.RED + "You do not have permission to send that command!");
			}
			else
				player.sendMessage(ChatColor.RED + "Invalid usage!");
		}
		else
			sender.sendMessage(ChatColor.RED + "You must be a player to execute this command!");
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		ArrayList<String> result = new ArrayList<String>();

		if(sender.hasPermission("showdown.admin")) {
			result.add("start");
			result.add("stop");
			result.add("arena");
		}

		return result;
	}

}