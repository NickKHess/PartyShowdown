package com.csg.ware.rounds.rounds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.csg.utils.items.ItemStackBuilder;
import com.csg.utils.player.PlayerCollisionToggler;
import com.csg.ware.entities.GamePlayer;
import com.csg.ware.entities.director.GameDirector;
import com.csg.ware.events.RoundSnowballingEventManager;

public final class RoundSnowballing extends Round {

	ItemStack shovel;
	ItemStack snowball16 = new ItemStack(Material.SNOWBALL, 16);
	ItemStack snowball = new ItemStack(Material.SNOWBALL);

	int[] snowballSlots = new int[] {0, 1, 2};

	public RoundSnowballing() {
		super("Snowballing", "Eliminate other players using snowballs!", 300, new RoundSnowballingEventManager());
		
		shovel = new ItemStackBuilder(Material.NETHERITE_SHOVEL).setName("Pog Shovel").build();
	}

	@Override
	public void startGame() {
		for(GamePlayer gPlayer : GameDirector.instance().getPlayers()) {
			Player player = Bukkit.getPlayer(gPlayer.getUUID());
			
			// Re-enable collision for all players
			PlayerCollisionToggler toggler = new PlayerCollisionToggler();
			toggler.removePlayer(player);
			
			// All players in the session will receive snowballs
			for(int slot : snowballSlots)
				player.getInventory().setItem(slot, snowball16);
			player.getInventory().setItem(8, shovel);
		}
	}
	
	@Override
	public void gameLoop() {
		// TODO: Idk if anything would actually go here this time around
		// This game is mostly event-based anyway
	}

	@Override
	public void endGame() {
		// TODO: Clear inventories
	}

	@Override
	public void postGame() {
		// TODO: Show leaderboards, show
	}

}