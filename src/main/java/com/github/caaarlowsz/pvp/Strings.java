package com.github.caaarlowsz.pvp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public final class Strings {

	private static FileConfiguration config = PvPBasic.getPlugin().getConfig();

	public static String getJoinMessage(String playerName) {
		return color(config.getString("join-message")).replace("{player_name}", playerName);
	}

	public static String getQuitMessage(String playerName) {
		return color(config.getString("quit-message")).replace("{player_name}", playerName);
	}

	public static String getDeathMessage(String playerName) {
		return color(config.getString("death-message")).replace("{player_name}", playerName);
	}

	public static String getKillMessage(String playerName, String killerName) {
		return color(config.getString("kill-message")).replace("{player_name}", playerName).replace("{killer_name}",
				killerName);
	}

	public static String getCommandOnlyPlayers() {
		return color(config.getString("command-only-players"));
	}

	public static String getSetSpawnSuccess(int x, int y, int z) {
		return color(config.getString("setspawn-success")).replace("{x}", String.valueOf(x))
				.replace("{y}", String.valueOf(y)).replace("{z}", String.valueOf(z));
	}

	public static String getToSpawn() {
		return color(config.getString("to-spawn"));
	}

	public static String getWaitToSpawn() {
		return color(config.getString("wait-to-spawn"));
	}

	public static String getAlreadyInSpawn() {
		return color(config.getString("already-in-spawn"));
	}

	public static void setSpawnLocation(Location loc) {
		config.set("spawn-location.world", loc.getWorld().getName());
		config.set("spawn-location.x", loc.getX());
		config.set("spawn-location.y", loc.getY());
		config.set("spawn-location.z", loc.getZ());
		config.set("spawn-location.yaw", loc.getYaw());
		config.set("spawn-location.pitch", loc.getPitch());
		PvPBasic.getPlugin().saveConfig();
	}

	public static Location getSpawnLocation() {
		World world = Bukkit.getWorld(config.getString("spawn-location.world"));
		double x = config.getDouble("spawn-location.x"), y = config.getDouble("spawn-location.y"),
				z = config.getDouble("spawn-location.z");
		float yaw = config.getLong("spawn-location.yaw");
		float pitch = config.getLong("spawn-location.pitch");
		return new Location(world, x, y, z, yaw, pitch);
	}

	public static KitSelector getKitSelector() {
		return new KitSelector();
	}

	public static class KitSelector {

		public Stack getIcon() {
			String[] split = config.getString("kits-selector.material").split(",");
			String type = split.length > 0 ? split[0] : "AIR", amount = split.length > 1 ? split[1] : "1",
					durability = split.length > 2 ? split[1] : "0";
			return new Stack(Material.getMaterial(type), Integer.valueOf(amount), Integer.valueOf(durability),
					color(config.getString("kits-selector.display-name")));
		}

		public String getName() {
			return color(config.getString("kits-inventory.name"));
		}

		public String getSelectedKit() {
			return color(config.getString("kits-inventory.selected-kit"));
		}

		public String getSelectKit() {
			return color(config.getString("kits-inventory.select-kit"));
		}

		public String getYouSelectKitSuccess(String kitName) {
			return color(config.getString("kits-inventory.you-select-kit-success").replace("{kit_name}", kitName));
		}
	}

	private static String color(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}
}