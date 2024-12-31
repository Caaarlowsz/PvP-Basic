package com.github.caaarlowsz.pvp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

public final class KitsGUI implements Listener {

	public static final Stack ICON = new Stack(Material.CHEST, "§aKit selector §7(right click)");

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerInteract(PlayerInteractEvent event) {
		PvPPlayer player = PvPBasic.getPlayer(event.getPlayer());
		if (event.hasItem() && event.getItem().isSimilar(ICON.toItemStack())) {
			if (player.isProtected()) {
				event.setCancelled(true);
				if (event.getAction().name().contains("RIGHT"))
					openGUI(player);
			} else
				player.getInventory().removeItem(ICON.toItemStack());
		}
	}

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && event.getInventory().getName().equals("Kit selector")
				&& event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
				&& event.getCurrentItem().getItemMeta().hasDisplayName()) {
			String display = event.getCurrentItem().getItemMeta().getDisplayName();
			PvPPlayer player = PvPBasic.getPlayer((Player) event.getWhoClicked());
			event.setCancelled(true);

			if (display.startsWith("§aKit ")) {
				String kit = display.substring(6);
				player.sendMessage("§aYou have selected the " + kit.toLowerCase() + " kit.");
			}
		}
	}

	private void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "Kit selector");

		inv.setItem(10, new Stack(Material.DIAMOND_SWORD, "§aKit Knight", "§7Description...", "", "§eClick to select")
				.flag(ItemFlag.HIDE_ATTRIBUTES));
		inv.setItem(11, new Stack(Material.BOW, "§aKit Archer", "§7Description...", "", "§eClick to select"));
		inv.setItem(12,
				new Stack(Material.DIAMOND_CHESTPLATE, "§aKit Tank", "§7Description...", "", "§eClick to select"));
		inv.setItem(13, new Stack(Material.STICK, "§aKit Grandpa", "§7Description...", "", "§eClick to select"));
		inv.setItem(14, new Stack(Material.GOLDEN_APPLE, "§aKit Healer", "§7Description...", "", "§eClick to select"));
		inv.setItem(15,
				new Stack(Material.ENDER_PEARL, "§aKit teleporter", "§7Description...", "", "§eClick to select"));
		inv.setItem(16,
				new Stack(Material.ENCHANTMENT_TABLE, "§aKit Enchanter", "§7Description...", "", "§eClick to select"));

		player.openInventory(inv);
	}
}