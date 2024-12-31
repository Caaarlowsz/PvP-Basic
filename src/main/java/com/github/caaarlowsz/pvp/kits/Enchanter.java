package com.github.caaarlowsz.pvp.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.pvp.Kit;
import com.github.caaarlowsz.pvp.Stack;

public final class Enchanter extends Kit {

	public Enchanter() {
		super("Enchanter", new Stack(Material.ENCHANTMENT_TABLE));
	}

	@Override
	public void giveItems(Player player) {
		super.giveItems(player);

		PlayerInventory inv = player.getInventory();
		inv.setItem(16, new Stack(Material.INK_SACK, 64, 4).unbreak());
		inv.setItem(17, new Stack(Material.EXP_BOTTLE, 64).unbreak());
	}
}