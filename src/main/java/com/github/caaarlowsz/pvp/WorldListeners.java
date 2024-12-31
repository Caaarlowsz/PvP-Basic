package com.github.caaarlowsz.pvp;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public final class WorldListeners implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.hasItem() && event.getMaterial() == Material.MUSHROOM_SOUP
				&& event.getAction().name().contains("RIGHT") && player.getHealth() != player.getMaxHealth()) {
			event.setCancelled(true);
			player.setHealth((player.getHealth() < player.getMaxHealth() - 7D) ? player.getHealth() + 7D
					: player.getMaxHealth());
			event.getItem().setType(Material.BOWL);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerDroPitem(PlayerDropItemEvent event) {
		ItemStack item = event.getItemDrop().getItemStack();
		event.setCancelled(PvPBasic.getPlayer(event.getPlayer()).isProtected()
				|| (item.hasItemMeta() && item.getItemMeta().spigot().isUnbreakable()));
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerPickupItem(PlayerPickupItemEvent event) {
		ItemStack item = event.getItem().getItemStack();
		event.setCancelled(PvPBasic.getPlayer(event.getPlayer()).isProtected()
				|| (item.hasItemMeta() && item.getItemMeta().spigot().isUnbreakable()));
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onBlockPlace(BlockPlaceEvent event) {
		event.setCancelled(event.getPlayer().getGameMode() != GameMode.CREATIVE);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onEntityRegainHealth(EntityRegainHealthEvent event) {
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onFoodLevelChange(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onWeatherChange(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
}