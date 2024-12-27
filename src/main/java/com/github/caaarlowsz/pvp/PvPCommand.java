package com.github.caaarlowsz.pvp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class PvPCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("pvp.manage")) {
			sender.hasPermission("§cVocê não possui permissão para usar este comando.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§cUse: /" + label + " [setspawn]");
			return true;
		}

		if (args[0].equalsIgnoreCase("setspawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas jogadores podem usar este comando.");
				return true;
			}

			Location loc = ((Player) sender).getLocation();
			loc.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			sender.sendMessage("§aVocê definiu a posição do spawn em " + loc.getBlockX() + ", " + loc.getBlockY() + ", "
					+ loc.getBlockZ() + ".");
			return true;
		}
		return true;
	}
}