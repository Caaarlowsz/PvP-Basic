package com.github.caaarlowsz.pvp;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
				player.setKit(PvPBasic.getKit(kit));
				update(player, event.getInventory());
				player.sendMessage("§aYou have selected the " + kit.toLowerCase() + " kit.");
			}
		}
	}

	private void update(PvPPlayer player, Inventory inv) {
		inv.clear();

		Stack glass = new Stack(Material.THIN_GLASS);
		for (int i = 0; i < 10; i++)
			inv.setItem(i, glass);
		for (int i = 17; i < 27; i++)
			inv.setItem(i, glass);
		PvPBasic.getKits().forEach(kit -> {
			ItemStack icon = kit.getIcon().clone();
			ItemMeta mIcon = icon.getItemMeta();
			if (player.getKit() == kit) {
				if (icon.getType() == Material.GOLDEN_APPLE)
					icon.setDurability((short) 1);
				else
					mIcon.addEnchant(Enchantment.DAMAGE_ALL, 0, false);
			}
			mIcon.setDisplayName("§aKit " + kit.getName());
			mIcon.setLore(Arrays.asList("§7Description...", "",
					player.getKit() == kit ? "§6You have selected this kit" : "§eClick to select"));
			icon.setItemMeta(mIcon);
			inv.addItem(icon);
		});

		inv.remove(glass.toItemStack());
	}

	private void openGUI(Player bukkitPlayer) {
		PvPPlayer player = PvPBasic.getPlayer(bukkitPlayer);
		Inventory inv = Bukkit.createInventory(null, 27, "Kit selector");
		update(player, inv);
		player.openInventory(inv);
	}
}