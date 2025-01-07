package com.github.caaarlowsz.pvp.kits;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import com.github.caaarlowsz.pvp.Kit;
import com.github.caaarlowsz.pvp.PvPBasic;
import com.github.caaarlowsz.pvp.PvPPlayer;
import com.github.caaarlowsz.pvp.Stack;

public final class Healer extends Kit implements Listener {

	public Healer() {
		super("Healer", "Good nutrition brings vitality.", new Stack(Material.GOLDEN_APPLE));
	}

	@Override
	public void giveItems(Player player) {
		super.giveItems(player);
		player.getInventory().addItem(new Stack(Material.GOLDEN_APPLE, 64).unbreak());
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerItemConsume(PlayerItemConsumeEvent event) {
		PvPPlayer player = PvPBasic.getPlayer(event.getPlayer());
		if (event.getItem().getType() == Material.GOLDEN_APPLE && player.getKit() instanceof Healer
				&& new Random().nextInt(64) <= 2)
			player.getInventory().addItem(new Stack(Material.GOLDEN_APPLE, 1, 1, "§dApple Notch").unbreak());
	}
}