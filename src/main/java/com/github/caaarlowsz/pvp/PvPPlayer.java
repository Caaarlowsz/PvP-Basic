package com.github.caaarlowsz.pvp;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;

public class PvPPlayer extends CraftPlayer {

	private boolean protection = false;
	private Kit kit;

	public PvPPlayer(Player player) {
		super((CraftServer) Bukkit.getServer(), ((CraftPlayer) player).getHandle());
	}

	public boolean isProtected() {
		return this.protection;
	}

	public void setProtection(boolean protection) {
		this.protection = protection;
	}

	public void reset() {
		this.setExp(0);
		this.setLevel(0);
		this.setFireTicks(0);
		this.setExhaustion(0);
		this.setFoodLevel(20);
		this.setSaturation(0);
		this.setNoDamageTicks(0);
		this.setHealthScale(20);
		this.setMaxHealth(20);
		this.setHealth(20);
		this.setGameMode(GameMode.ADVENTURE);
		this.getActivePotionEffects().forEach(e -> this.removePotionEffect(e.getType()));
		this.setAllowFlight(false);
		this.setFlying(false);

		this.getInventory().setArmorContents(null);
		this.getInventory().clear();
	}

	public boolean hasKit() {
		return this.kit != null;
	}

	public Kit getKit() {
		return this.hasKit() ? this.kit : PvPBasic.DEFAULT_KIT;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	public void removeKit() {
		this.kit = null;
	}

	public void giveKit() {
		this.reset();
		this.setProtection(false);

		PlayerInventory inv = this.getInventory();
		inv.setHelmet(new Stack(Material.IRON_HELMET).unbreak());
		inv.setChestplate(new Stack(Material.IRON_CHESTPLATE).unbreak());
		inv.setLeggings(new Stack(Material.IRON_LEGGINGS).unbreak());
		inv.setBoots(new Stack(Material.IRON_BOOTS).unbreak());
		inv.setItem(0, new Stack(Material.DIAMOND_SWORD).unbreak().flag(ItemFlag.HIDE_ATTRIBUTES));

		inv.setItem(13, new Stack(Material.BOWL, 64));
		inv.setItem(14, new Stack(Material.RED_MUSHROOM, 64));
		inv.setItem(15, new Stack(Material.BROWN_MUSHROOM, 64));

		this.getKit().giveItems(this);

		for (int i = 0; i < 32; i++)
			inv.addItem(new Stack(Material.MUSHROOM_SOUP));
	}

	public boolean teleportToSpawn() {
		boolean success = this.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
		if (success) {
			this.reset();
			this.setProtection(true);

			PlayerInventory inv = this.getInventory();
			inv.setItem(0, KitsGUI.ICON);
		}
		return success;
	}
}