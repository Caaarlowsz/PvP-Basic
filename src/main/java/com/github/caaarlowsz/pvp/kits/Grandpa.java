package com.github.caaarlowsz.pvp.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import com.github.caaarlowsz.pvp.Kit;
import com.github.caaarlowsz.pvp.Stack;

public final class Grandpa extends Kit {

	public Grandpa() {
		super("Grandpa", new Stack(Material.STICK));
	}

	@Override
	public void giveItems(Player player) {
		super.giveItems(player);
		player.getInventory().addItem(new Stack(Material.STICK, "§aKnockback III §7(hit a player)")
				.ench(Enchantment.KNOCKBACK, 3).unbreak().flag(ItemFlag.HIDE_ENCHANTS));
	}
}