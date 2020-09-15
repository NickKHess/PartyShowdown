package com.csg.showdown.commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import com.csg.showdown.Showdown;
import com.csg.showdown.entities.director.GameDirector;
import com.csg.showdown.entities.director.GameDirector.Phase;
import net.md_5.bungee.api.ChatColor;

public final class CommandShowdown implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {
				GameDirector director = GameDirector.instance();
				if(args[0].equalsIgnoreCase("start")) {
					if(director.getPhase().equals(Phase.PREGAME))
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
				else if(args[0].equalsIgnoreCase("test")) {
					player.sendMessage("Bukkit " + Bukkit.getScheduler().getPendingTasks().toString());
					player.sendMessage("Plugin " + Showdown.getPlugin().getServer().getScheduler().getPendingTasks().toString());
				}
			}

		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		ArrayList<String> result = new ArrayList<String>();

		result.add("start");
		result.add("startcountdown");
		result.add("stopcountdown");
		result.add("stop");
		result.add("test");

		return result;
	}

}