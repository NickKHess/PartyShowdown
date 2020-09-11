package com.csg.ware.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.csg.ware.Ware;
import com.csg.ware.entities.director.GameDirector;
import com.csg.ware.entities.director.GameDirector.Phase;
import net.md_5.bungee.api.ChatColor;

public final class CommandWare implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {
				GameDirector director = GameDirector.instance();
				if(args[0].equalsIgnoreCase("start")) {
					if(director.getPhase().equals(Phase.NONE))
						if(director.getCurrentGame() == null)
							director.startRandomRound();
				}
				else if(args[0].equalsIgnoreCase("startcountdown")) {
					if(director.getCurrentGame() == null) {
						if(director.getPhase().equals(Phase.PREGAME)) {
							director.populatePlayers();
							director.startCountdown(10);
						}
					}
				}
				else if(args[0].equalsIgnoreCase("stopcountdown")) {
					if(director.getCurrentGame() == null)
						if(director.getPhase().equals(Phase.COUNTDOWN))
							director.setPhase(Phase.PREGAME);
				}
				else if(args[0].equalsIgnoreCase("stop")) {
					if(director.getCurrentGame() != null)
						director.stop();
					else
						player.sendMessage(ChatColor.RED + "There is no game running!");
				}
				else if(args[0].equalsIgnoreCase("tasks")) {
					player.sendMessage("Bukkit " + Bukkit.getScheduler().getPendingTasks().toString());
					player.sendMessage("Plugin " + Ware.getPlugin().getServer().getScheduler().getPendingTasks().toString());
				}
			}

		}
		return true;
	}


}
