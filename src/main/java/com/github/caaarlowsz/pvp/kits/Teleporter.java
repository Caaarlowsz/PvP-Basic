package com.github.caaarlowsz.pvp.kits;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.github.caaarlowsz.pvp.Kit;
import com.github.caaarlowsz.pvp.PvPBasic;
import com.github.caaarlowsz.pvp.PvPPlayer;
import com.github.caaarlowsz.pvp.Stack;

public final class Teleporter extends Kit implements Listener {

	public Teleporter() {
		super("Teleporter", "Teleport using your pearls.", new Stack(Material.ENDER_PEARL));
	}

	@Override
	public void giveItems(Player player) {
		super.giveItems(player);
		player.getInventory().addItem(new Stack(Material.ENDER_PEARL, 16).unbreak());
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void onProjectileHit(ProjectileLaunchEvent event) {
		if (event.getEntity() instanceof EnderPearl && event.getEntity().getShooter() instanceof Player) {
			PvPPlayer player = PvPBasic.getPlayer((Player) event.getEntity().getShooter());
			if (player.getKit() instanceof Teleporter && new Random().nextInt(16) <= 1)
				player.getInventory().addItem(new Stack(Material.ENDER_PEARL).unbreak());
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		PvPPlayer player = PvPBasic.getPlayer(event.getPlayer());
		if (event.getCause() == TeleportCause.ENDER_PEARL && player.getKit() instanceof Teleporter) {
			event.setCancelled(true);
			player.teleport(event.getTo());
		}
	}
}