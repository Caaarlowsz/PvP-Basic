package com.github.caaarlowsz.pvp.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import com.github.caaarlowsz.pvp.Kit;
import com.github.caaarlowsz.pvp.Stack;

public final class Knight extends Kit {

	public Knight() {
		super("Knight", new Stack(Material.DIAMOND_SWORD).flag(ItemFlag.HIDE_ATTRIBUTES));
	}

	@Override
	public void giveItems(Player player) {
		super.giveItems(player);
		player.getInventory().setItem(0, new Stack(Material.DIAMOND_SWORD).unbreak().ench(Enchantment.DAMAGE_ALL, 1)
				.flag(ItemFlag.HIDE_ATTRIBUTES));
	}
}