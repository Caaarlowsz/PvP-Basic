package com.github.caaarlowsz.pvp.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.pvp.Kit;
import com.github.caaarlowsz.pvp.Stack;

public final class Tank extends Kit {

	public Tank() {
		super("Tank", "Be tough as steel and take more damage.", new Stack(Material.DIAMOND_CHESTPLATE));
	}

	@Override
	public void giveItems(Player player) {
		super.giveItems(player);

		player.getInventory().setChestplate(new Stack(Material.DIAMOND_CHESTPLATE).unbreak());
		player.getInventory().setBoots(new Stack(Material.DIAMOND_BOOTS).unbreak());
	}
}