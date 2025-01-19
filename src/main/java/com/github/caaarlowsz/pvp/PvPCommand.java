package com.github.caaarlowsz.pvp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class PvPCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission(command.getPermission())) {
			sender.hasPermission(command.getPermissionMessage());
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(command.getUsage());
			return true;
		}

		if (args[0].equalsIgnoreCase("setspawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Strings.getCommandOnlyPlayers());
				return true;
			}

			Location loc = ((Player) sender).getLocation();
			Strings.setSpawnLocation(loc);
			sender.sendMessage(Strings.getSetSpawnSuccess(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
			return true;
		}
		return true;
	}
}