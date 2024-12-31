package com.github.caaarlowsz.pvp;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public final class KitListeners implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerMove(PlayerMoveEvent event) {
		PvPPlayer player = PvPBasic.getPlayer(event.getPlayer());
		if (event.getTo().clone().add(0, -0.1, 0).getBlock().getType() == Material.GRASS && player.isProtected())
			player.giveKit();
	}
}