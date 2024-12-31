package com.github.caaarlowsz.pvp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.github.caaarlowsz.pvp.PvPBasic;
import com.github.caaarlowsz.pvp.PvPPlayer;

public final class ProtectionListeners implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			PvPPlayer player = PvPBasic.getPlayer((Player) event.getEntity()),
					damager = PvPBasic.getPlayer((Player) event.getDamager());

			if (player.isProtected() && !damager.isProtected()
					&& player.getWorld().getSpawnLocation().distance(player.getLocation()) > 5)
				player.setProtection(false);
			event.setCancelled(player.isProtected());
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			PvPPlayer player = PvPBasic.getPlayer((Player) event.getEntity());

			event.setCancelled(player.isProtected());
			if (event.getCause() == DamageCause.FALL)
				player.setProtection(false);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerDropItem(PlayerDropItemEvent event) {
		event.setCancelled(PvPBasic.getPlayer(event.getPlayer()).isProtected());
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerPickupItem(PlayerPickupItemEvent event) {
		event.setCancelled(PvPBasic.getPlayer(event.getPlayer()).isProtected());
	}
}