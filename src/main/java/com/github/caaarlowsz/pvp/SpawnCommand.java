package com.github.caaarlowsz.pvp;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public final class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Strings.getCommandOnlyPlayers());
			return true;
		}

		PvPPlayer player = PvPBasic.getPlayer((Player) sender);
		if (player.getGameMode() == GameMode.CREATIVE && player.teleportToSpawn()) {
			player.giveKit();
			player.sendMessage(Strings.getToSpawn());
			return true;
		}

		PlayerInventory inv = player.getInventory();
		inv.setArmorContents(null);
		inv.clear();
		player.setHealth(4);
		player.sendMessage(Strings.getWaitToSpawn());
		Bukkit.getScheduler().runTaskLater(PvPBasic.getPlugin(), () -> {
			if (player.teleportToSpawn()) {
				player.giveKit();
				player.sendMessage(Strings.getToSpawn());
			}
		}, 60);
		return true;
	}
}