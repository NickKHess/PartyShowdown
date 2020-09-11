package com.csg.utils.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

public class PlayerCollisionToggler {

	Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
	Team noCollide;

	public PlayerCollisionToggler() {
		if(scoreboard.getTeam("NoCollide") != null)
			noCollide = scoreboard.getTeam("NoCollide");
		else
			noCollide = scoreboard.registerNewTeam("NoCollide");

		noCollide.setOption(Option.COLLISION_RULE, OptionStatus.NEVER);
	}

	public void addPlayer(Player player) {
		if(!noCollide.hasEntry(player.getName()))
			noCollide.addEntry(player.getName());
	}

	public void removePlayer(Player player) {
		if(noCollide.hasEntry(player.getName()))
			noCollide.removeEntry(player.getName());
	}

	public void togglePlayer(Player player) {
		if(!noCollide.hasEntry(player.getName()))
			noCollide.addEntry(player.getName());
		else
			noCollide.removeEntry(player.getName());
	}

}