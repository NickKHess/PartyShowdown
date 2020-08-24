package com.csg.ware.game.games;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.csg.ware.Ware;
import com.csg.ware.game.Game;

public class GameRandomTeleport extends Game {

	public GameRandomTeleport() {
		super("Random Teleport",
				"All players will be randomly teleported!");
	}

	public void startGame() {
		Random r = new Random();
		@SuppressWarnings("unchecked")
		List<Player> players = (List<Player>) Ware.getPlugin().getServer().getOnlinePlayers();
		List<Player> shuffled = players;
		Collections.shuffle(shuffled);

		
		if(r.nextFloat() < 0.1) {
			int i = 0;
			// Teleport players to each other
			for(Player player : players) {
				player.teleport(shuffled.get(i).getLocation());
				i++;
			}
		}
		else {
			// Teleport players randomly across the world
			for(Player player : players) {
				World world = player.getWorld();
				int x = r.nextInt(6000000) - 3000000;
				int z = r.nextInt(6000000) - 3000000;
				player.teleport(new Location(world, x, world.getHighestBlockYAt(x, z), z));
			}
		}
	}

}