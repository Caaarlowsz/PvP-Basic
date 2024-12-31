package com.github.caaarlowsz.pvp.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.caaarlowsz.pvp.PvPBasic;
import com.github.caaarlowsz.pvp.PvPPlayer;

public final class KitListeners implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerMove(PlayerMoveEvent event) {
		PvPPlayer player = PvPBasic.getPlayer(event.getPlayer());
		if (event.getTo().clone().add(0, -0.1, 0).getBlock().getType() == Material.GRASS && player.isProtected())
			player.giveKit();
	}
}