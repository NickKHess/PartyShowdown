package com.csg.ware.game.archive;

import java.util.List;
import java.util.Random;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import com.csg.ware.Ware;
import com.csg.ware.game.Game;

public class GameDropInventory extends Game {

	public GameDropInventory() {
		super("Drop Inventory",
				"All players will drop their inventories!");
	}

	public void performGameAction() {
		Random r = new Random();
		@SuppressWarnings("unchecked")
		List<Player> players = (List<Player>) Ware.getPlugin().getServer().getOnlinePlayers();

		for(Player player : players) {
			Inventory inventory = player.getInventory();
			for(int i = 0; i <= inventory.getSize(); i++) {
				Item item = null;
				try {
					item = player.getWorld().dropItemNaturally(player.getLocation(), inventory.getItem(i));

					switch (i) {
					case 0:
						item = player.getWorld().dropItemNaturally(player.getLocation(), ((PlayerInventory) inventory).getBoots());
						break;
					case 1:
						item = player.getWorld().dropItemNaturally(player.getLocation(), ((PlayerInventory) inventory).getChestplate());
						break;
					case 2:
						item = player.getWorld().dropItemNaturally(player.getLocation(), ((PlayerInventory) inventory).getLeggings());
						break;
					case 3:
						item = player.getWorld().dropItemNaturally(player.getLocation(), ((PlayerInventory) inventory).getHelmet());
						break;
					}

					item.setVelocity(new Vector(r.nextDouble() * 3, r.nextDouble() * 3, r.nextDouble() * 3));
					item.setPickupDelay(40);
				} catch(Exception e) {
					// No need to take action if there's nothing in the slot
				}
			}
			inventory.clear();
		}
	}

}