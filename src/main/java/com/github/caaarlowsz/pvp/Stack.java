package com.github.caaarlowsz.pvp;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Stack extends ItemStack {

	public Stack(Material type) {
		this(type, 1);
	}

	public Stack(Material type, int amount) {
		this(type, amount, 0);
	}

	public Stack(Material type, int amount, int durability) {
		super(type, amount, (short) durability);
	}

	public Stack(Material type, String display, String... lore) {
		this(type, 1, display, lore);
	}

	public Stack(Material type, int amount, String display, String... lore) {
		this(type, amount, 0, display, lore);
	}

	public Stack(Material type, int amount, int durability, String display, String... lore) {
		super(type, amount, (short) durability);
		ItemMeta meta = this.getItemMeta();
		meta.setDisplayName(display);
		meta.setLore(Arrays.asList(lore));
		this.setItemMeta(meta);
	}

	public Stack ench(Enchantment enchantment, int level) {
		this.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	public Stack flag(ItemFlag... flags) {
		ItemMeta meta = this.getItemMeta();
		meta.addItemFlags(flags);
		this.setItemMeta(meta);
		return this;
	}

	public Stack unbreak() {
		ItemMeta meta = this.getItemMeta();
		meta.spigot().setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		this.setItemMeta(meta);
		return this;
	}

	public ItemStack toItemStack() {
		return ItemStack.deserialize(this.serialize());
	}
}