package com.github.caaarlowsz.pvp.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.pvp.Kit;
import com.github.caaarlowsz.pvp.Stack;

public final class Archer extends Kit {

	public Archer() {
		super("Archer", new Stack(Material.BOW));
	}

	@Override
	public void giveItems(Player player) {
		super.giveItems(player);

		player.getInventory().addItem(new Stack(Material.BOW).ench(Enchantment.ARROW_DAMAGE, 1)
				.ench(Enchantment.ARROW_INFINITE, 1).unbreak());
		player.getInventory().setItem(16, new Stack(Material.ARROW).unbreak());
	}
}