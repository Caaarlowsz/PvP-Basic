package com.github.caaarlowsz.pvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class KitCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Strings.getCommandOnlyPlayers());
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(command.getUsage());
			return true;
		}

		Kit kit = PvPBasic.getKit(args[0]);
		if (kit == null) {
			sender.sendMessage(Strings.getKitNotFound(args[0]));
			return true;
		}

		PvPPlayer player = PvPBasic.getPlayer((Player) sender);
		if (!player.isProtected()) {
			player.sendMessage(Strings.getAlreadyKit(player.getKit().getName()));
			return true;
		}
		if (!player.hasPermission("kit." + kit.getName()) && PvPBasic.DEFAULT_KIT != kit) {
			player.sendMessage(Strings.getNoPermissionKit(kit.getName()));
			return true;
		}
		if (player.getKit() == kit) {
			player.sendMessage(Strings.getKitSelector().getAlreadySelectedKit());
			return true;
		}

		player.setKit(kit);
		player.sendMessage(Strings.getKitSelector().getYouSelectKitSuccess(kit.getName()));
		return true;
	}
}