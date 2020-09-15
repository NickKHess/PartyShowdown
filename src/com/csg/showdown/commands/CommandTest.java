package com.csg.showdown.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.csg.utils.player.PlayerCollisionToggler;

public final class CommandTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("collision")) {
					PlayerCollisionToggler toggler = new PlayerCollisionToggler();
					toggler.togglePlayer(player);
				}
			}

		}
		return true;
	}


}
