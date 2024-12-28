package com.github.caaarlowsz.pvp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class PvPCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission(cmd.getPermission())) {
			sender.hasPermission(cmd.getPermissionMessage());
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(cmd.getUsage());
			return true;
		}

		if (args[0].equalsIgnoreCase("setspawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Strings.getCommandOnlyPlayers());
				return true;
			}

			Location loc = ((Player) sender).getLocation();
			loc.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			sender.sendMessage(Strings.getSetSpawnSuccess().replace("{x}", String.valueOf(loc.getBlockX()))
					.replace("{y}", String.valueOf(loc.getBlockY())).replace("{z}", String.valueOf(loc.getBlockZ())));
			return true;
		}
		return true;
	}
}