package com.github.caaarlowsz.pvp;

import java.util.ArrayList;

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

	public static Stack getIcon() {
		return Strings.getKitSelector().getIcon();
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onPlayerInteract(PlayerInteractEvent event) {
		PvPPlayer player = PvPBasic.getPlayer(event.getPlayer());
		if (event.hasItem() && event.getItem().isSimilar(getIcon().toItemStack())) {
			if (player.isProtected()) {
				event.setCancelled(true);
				if (event.getAction().name().contains("RIGHT"))
					openGUI(player);
			} else
				player.getInventory().removeItem(getIcon().toItemStack());
		}
	}

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player
				&& event.getInventory().getName().equals(Strings.getKitSelector().getName())
				&& event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
				&& event.getCurrentItem().getItemMeta().hasDisplayName()) {
			String display = event.getCurrentItem().getItemMeta().getDisplayName();
			PvPPlayer player = PvPBasic.getPlayer((Player) event.getWhoClicked());
			event.setCancelled(true);

			if (display.startsWith("§aKit ")) {
				Kit kit = PvPBasic.getKit(display.substring(6));
				if (player.getKit() != kit) {
					player.setKit(kit);
					update(player, event.getInventory());
					player.sendMessage(Strings.getKitSelector().getYouSelectKitSuccess(kit.getName()));
				} else
					player.sendMessage(Strings.getKitSelector().getAlreadySelectedKit());
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
		PvPBasic.getKits().stream()
				.filter(kit -> player.hasPermission("kit." + kit.getName()) || PvPBasic.DEFAULT_KIT == kit)
				.forEach(kit -> {
					ItemStack icon = kit.getIcon().clone();
					ItemMeta mIcon = icon.getItemMeta();
					if (player.getKit() == kit) {
						if (icon.getType() == Material.GOLDEN_APPLE)
							icon.setDurability((short) 1);
						else
							mIcon.addEnchant(Enchantment.DAMAGE_ALL, 0, false);
					}
					mIcon.setDisplayName("§aKit " + kit.getName());

					ArrayList<String> lore = new ArrayList<>();
					String line = new String();
					for (String word : kit.getDescription().split(" ")) {
						if (line.split(" ").length >= 5 || line.length() >= 40) {
							lore.add("§7" + line);
							line = new String();
						}
						line += (line.isEmpty() ? "" : " ") + word;
					}
					lore.add("§7" + line);
					lore.add("");
					lore.add(player.getKit() == kit ? Strings.getKitSelector().getSelectedKit()
							: Strings.getKitSelector().getSelectKit());
					mIcon.setLore(lore);
					icon.setItemMeta(mIcon);
					inv.addItem(icon);
				});

		inv.remove(glass.toItemStack());
	}

	private void openGUI(Player bukkitPlayer) {
		PvPPlayer player = PvPBasic.getPlayer(bukkitPlayer);
		Inventory inv = Bukkit.createInventory(null, 27, Strings.getKitSelector().getName());
		update(player, inv);
		player.openInventory(inv);
	}
}