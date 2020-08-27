package com.csg.ware.rounds.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder {
	
	private ItemStack itemStack;
	private ItemMeta itemMeta;
	
	/**
	 * <i>ItemStackBuilder</i>
	 * <br>
	 * Builds fancy, complicated ItemStacks
	 * 
	 * @param material - The material to be used in the ItemStack
	 */
	public ItemStackBuilder(Material material) {
		itemStack = new ItemStack(material);
		itemMeta = itemStack.getItemMeta();
	}
	
	public ItemStackBuilder(Material material, int amount) {
		itemStack = new ItemStack(material, amount);
		itemMeta = itemStack.getItemMeta();
	}
	
	public ItemStackBuilder setName() {
		
	}
	
	public ItemStackBuilder setLore() {
		//TODO
	}
	
	public ItemStackBuilder setEnchants() {
		//TODO
	}
	
	public ItemStack build() {
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
}