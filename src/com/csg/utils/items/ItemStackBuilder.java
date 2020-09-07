package com.csg.utils.items;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.minecraft.server.v1_16_R2.NBTBase;
import net.minecraft.server.v1_16_R2.NBTTagByte;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import net.minecraft.server.v1_16_R2.NBTTagDouble;
import net.minecraft.server.v1_16_R2.NBTTagFloat;
import net.minecraft.server.v1_16_R2.NBTTagInt;
import net.minecraft.server.v1_16_R2.NBTTagLong;
import net.minecraft.server.v1_16_R2.NBTTagShort;
import net.minecraft.server.v1_16_R2.NBTTagString;

public class ItemStackBuilder {

	private net.minecraft.server.v1_16_R2.ItemStack nmsStack;
	private ItemStack itemStack;
	private ItemMeta itemMeta;

	/**
	 * <i>ItemStackBuilder</i>
	 * <br>
	 * Builds fancy, complicated {@link ItemStack}s.
	 * {@link ItemMeta} is not set until the end of building.
	 * 
	 * @param material - The item material
	 * @param amount - The amount of the item
	 */
	public ItemStackBuilder(Material material, int amount) {
		itemStack = new ItemStack(material, amount);
		itemMeta = itemStack.getItemMeta();
		nmsStack = CraftItemStack.asNMSCopy(itemStack);
		setNBTTag("CSU", true);
	}

	/**
	 * <i>ItemStackBuilder</i>
	 * <br>
	 * Builds fancy, complicated {@link ItemStack}s.
	 * {@link ItemMeta} is not set until the end of building.
	 * 
	 * @param material - The item material
	 */
	public ItemStackBuilder(Material material) {
		this(material, 1);
	}

	// ITEM META

	/**
	 * <i>setName</i>
	 * <br>
	 * Sets the display name of the {@link ItemStack}
	 * 
	 * @param name
	 * @return {@link ItemStackBuilder}
	 */
	public ItemStackBuilder setName(String name) {
		itemMeta.setDisplayName(name);
		return this;
	}

	/**
	 * <i>setLore</i>
	 * <br>
	 * Sets the lore of the {@link ItemStack}
	 * 
	 * @param lore
	 * @return {@link ItemStackBuilder}
	 */
	public ItemStackBuilder setLore(List<String> lore) {
		itemMeta.setLore(lore);
		return this;
	}

	/**
	 * <i>addEnchant</i>
	 * <br>
	 * Adds an enchant to the {@link ItemStack}
	 * 
	 * @param enchant
	 * @param level
	 * @param ignoreLevelRestriction
	 * @return {@link ItemStackBuilder}
	 */
	public ItemStackBuilder addEnchant(Enchantment enchant, int level, boolean ignoreLevelRestriction) {
		itemMeta.addEnchant(enchant, level, ignoreLevelRestriction);
		return this;
	}

	/**
	 * <i>setUnbreakable</i>
	 * <br>
	 * Sets whether the {@link ItemStack} is unbreakable
	 * 
	 * @param unbreakable
	 * @return {@link ItemStackBuilder}
	 */
	public ItemStackBuilder setUnbreakable(boolean unbreakable) {
		itemMeta.setUnbreakable(unbreakable);
		return this;
	}

	/**
	 * <i>setUnbreakable</i>
	 * <br>
	 * Sets whether the {@link ItemStack} is unbreakable
	 * 
	 * @param unbreakable
	 * @return {@link ItemStackBuilder}
	 */
	public ItemStackBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
		itemMeta.addAttributeModifier(attribute, modifier);
		return this;
	}

	// NMS

	/**
	 * <i>setNBTTag</i>
	 * <br>
	 * Maps a key to a given value in the {@link NBTTagCompound} of the {@link ItemStack}
	 * 
	 * @param key
	 * @param value
	 * @return {@link ItemStackBuilder}
	 */
	public <T extends Object> ItemStackBuilder setNBTTag(String key, T value) {
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();

		// Set the NBT tag based on the object
		if(value instanceof Integer) compound.set(key, NBTTagInt.a((int) value));
		else if(value instanceof String) compound.set(key, NBTTagString.a((String) value));
		else if(value instanceof Byte) compound.set(key, NBTTagByte.a((byte) value));
		else if(value instanceof Double) compound.set(key, NBTTagDouble.a((double) value));
		else if(value instanceof Float) compound.set(key, NBTTagFloat.a((float) value));
		else if(value instanceof Long) compound.set(key, NBTTagLong.a((long) value));
		else if(value instanceof Short) compound.set(key, NBTTagShort.a((short) value));

		return this;
	}

	/**
	 * <i>getNBTTag</i>
	 * <br>
	 * Gets the value mapped to a key in the {@link NBTTagCompound} of the {@link ItemStack}
	 * 
	 * @param key
	 * @return {@link NBTBase}
	 */
	public NBTBase getNBTTag(String key) {
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();

		if(compound.getKeys().contains(key))
			return compound.get(key);
		return null;
	}

	// BUILDER
	
	/**
	 * <i>build</i>
	 * <br>
	 * Builds the {@link ItemStackBuilder} into an {@link ItemStack} after setting the {@link} ItemMeta
	 * 
	 * @return {@link ItemStack}
	 */
	public ItemStack build() {
		itemStack.setItemMeta(itemMeta);
		return (ItemStack) itemStack;
	}

	/**
	 * <i>fromItemStack</i>
	 * <br>
	 * Creates an {@link ItemStackBuilder} from an existing {@link ItemStack}
	 * 
	 * @return {@link ItemStackBuilder}
	 */
	public static ItemStackBuilder fromItemStack(ItemStack itemStack) {
		ItemStackBuilder newBuilder = new ItemStackBuilder(itemStack.getType(), itemStack.getAmount());
		newBuilder.itemMeta = itemStack.getItemMeta();
		return newBuilder;
	}

	/**
	 * <i>isCustomItemStack</i>
	 * <br>
	 * Checks whether the {@link ItemStack} has the "CSU" tag
	 * 
	 * @return {@link Boolean}
	 */
	public static boolean isCustomItemStack(ItemStack itemStack) {
		return ItemStackBuilder.fromItemStack(itemStack).getNBTTag("CSU") != null;
	}

}