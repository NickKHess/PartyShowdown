package com.csg.ware.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.csg.ware.entities.GameDirector;

public final class CommandWare implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("start")) {
					if(GameDirector.instance().getPhase() == 0)
						GameDirector.instance().startRandomGame();
				}
			}

		}
		return true;
	}


}
