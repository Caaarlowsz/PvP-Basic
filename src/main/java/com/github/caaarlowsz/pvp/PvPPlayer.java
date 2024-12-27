package com.github.caaarlowsz.pvp;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PvPPlayer extends CraftPlayer {

	private boolean protection = false;

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

	public boolean teleportToSpawn() {
		boolean success = this.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
		this.setProtection(success);
		return success;
	}
}