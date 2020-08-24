package com.csg.ware.game.games;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.csg.ware.Ware;
import com.csg.ware.game.Game;
import com.csg.ware.game.objects.GameTeam;

public class GameSnowballFight extends Game {
	
	GameTeam blueTeam = new GameTeam("Blue", ChatColor.BLUE);
	GameTeam redTeam = new GameTeam("Red", ChatColor.RED);
	
	ItemStack snowball16 = new ItemStack(Material.SNOWBALL, 16);
	ItemStack snowball = new ItemStack(Material.SNOWBALL);
	
	int[] snowballSlots = new int[] {0, 1, 2};

	public GameSnowballFight() {
		super("Snowball Fight", "Eliminate other players using snowballs!", 5);
	}

	@Override
	public void startGame() {
		for(UUID uuid : Ware.getPlugin().getPlayers()) {
			Player player = Bukkit.getPlayer(uuid);
			for(int slot : snowballSlots)
				player.getInventory().setItem(slot, snowball16);
			
		}
	}

	@Override
	public void gameTask() {
		
	}
	
	@Override
	public void endGame() {
		//TODO
	}

}