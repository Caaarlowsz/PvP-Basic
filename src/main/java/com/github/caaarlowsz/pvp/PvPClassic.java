package com.github.caaarlowsz.pvp;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PvPClassic extends JavaPlugin {

	private static LinkedHashMap<UUID, PvPPlayer> playerMap = new LinkedHashMap<>();

	public static Collection<PvPPlayer> getPlayers() {
		return Collections.unmodifiableCollection(playerMap.values());
	}

	public static PvPPlayer getPlayer(Player player) {
		PvPPlayer pvpPlayer = playerMap.get(player.getUniqueId());
		if (pvpPlayer == null)
			playerMap.put(player.getUniqueId(), pvpPlayer = new PvPPlayer(player));
		return pvpPlayer;
	}

	public static void removePlayer(Player player) {
		playerMap.remove(player.getUniqueId());
	}

	public static PvPClassic getPlugin() {
		return getPlugin(PvPClassic.class);
	}

	@Override
	public void onEnable() {
		super.onEnable();

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListeners(), this);
		pm.registerEvents(new ProtectionListeners(), this);
		pm.registerEvents(new WorldListeners(), this);

		this.getCommand("pvp").setExecutor(new PvPCommand());
		this.getCommand("spawn").setExecutor(new SpawnCommand());
	}

	@Override
	public void onDisable() {
		super.onDisable();
		Bukkit.getScheduler().cancelTasks(this);
		HandlerList.unregisterAll(this);
	}
}