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
				if(args[0].equalsIgnoreCase("start")) {
					if(GameDirector.instance().getPhase().equals(Phase.NONE))
						if(GameDirector.instance().getCurrentGame() == null)
							GameDirector.instance().startRandomGame();
				}
				else if(args[0].equalsIgnoreCase("startcountdown")) {
					if(GameDirector.instance().getCurrentGame() != null)
						if(GameDirector.instance().getPhase().equals(Phase.PREGAME))
							GameDirector.instance().setPhase(Phase.COUNTDOWN);
				}
				else if(args[0].equalsIgnoreCase("stopcountdown")) {
					if(GameDirector.instance().getCurrentGame() != null)
						if(GameDirector.instance().getPhase().equals(Phase.COUNTDOWN))
							GameDirector.instance().setPhase(Phase.PREGAME);
				}
				else if(args[0].equalsIgnoreCase("stop")) {
					if(GameDirector.instance().getCurrentGame() != null)
						GameDirector.instance().stop();
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
