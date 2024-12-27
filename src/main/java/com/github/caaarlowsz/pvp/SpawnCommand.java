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
			sender.sendMessage("§cApenas jogadores podem usar este comando.");
			return true;
		}

		PvPPlayer player = PvPClassic.getPlayer((Player) sender);
		if (player.getGameMode() == GameMode.CREATIVE && player.teleportToSpawn()) {
			player.giveKit();
			player.sendMessage("§aTeleportado para o spawn.");
			return true;
		}

		PlayerInventory inv = player.getInventory();
		inv.setArmorContents(null);
		inv.clear();
		player.setHealth(4);
		player.sendMessage("§6Teleportando para o spawn... §8(aguarde 3s)");
		Bukkit.getScheduler().runTaskLater(PvPClassic.getPlugin(), () -> {
			if (player.teleportToSpawn()) {
				player.giveKit();
				player.sendMessage("§aTeleportado para o spawn.");
			}
		}, 60);
		return true;
	}
}