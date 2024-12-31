package com.github.caaarlowsz.pvp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

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
}