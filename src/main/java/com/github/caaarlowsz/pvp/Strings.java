package com.github.caaarlowsz.pvp;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public final class Strings {

	private static FileConfiguration config = PvPBasic.getPlugin().getConfig();

	public static String getJoinMessage() {
		return color(config.getString("join-message"));
	}

	public static String getQuitMessage() {
		return color(config.getString("quit-message"));
	}

	public static String getDeathMessage() {
		return color(config.getString("death-message"));
	}

	public static String getKillMessage() {
		return color(config.getString("kill-message"));
	}

	public static String getCommandOnlyPlayers() {
		return color(config.getString("command-only-players"));
	}

	public static String getSetSpawnSuccess() {
		return color(config.getString("setspawn-success"));
	}

	public static String getToSpawn() {
		return color(config.getString("to-spawn"));
	}

	public static String getWaitToSpawn() {
		return color(config.getString("wait-to-spawn"));
	}

	private static String color(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}
}