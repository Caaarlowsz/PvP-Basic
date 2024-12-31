package com.github.caaarlowsz.pvp;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.caaarlowsz.pvp.kits.Archer;
import com.github.caaarlowsz.pvp.kits.Enchanter;
import com.github.caaarlowsz.pvp.kits.Grandpa;
import com.github.caaarlowsz.pvp.kits.Healer;
import com.github.caaarlowsz.pvp.kits.Knight;
import com.github.caaarlowsz.pvp.kits.Tank;
import com.github.caaarlowsz.pvp.kits.Teleporter;
import com.github.caaarlowsz.pvp.listeners.KitListeners;
import com.github.caaarlowsz.pvp.listeners.PlayerListeners;
import com.github.caaarlowsz.pvp.listeners.ProtectionListeners;
import com.github.caaarlowsz.pvp.listeners.WorldListeners;

public final class PvPBasic extends JavaPlugin {

	private static LinkedHashMap<UUID, PvPPlayer> playerMap = new LinkedHashMap<>();
	private static LinkedHashSet<Kit> kits = new LinkedHashSet<>();
	public static final Kit DEFAULT_KIT = new Knight();

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

	public static Collection<Kit> getKits() {
		return Collections.unmodifiableSet(kits);
	}

	public static Kit getKit(String name) {
		return getKits().stream().filter(kit -> kit.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public static PvPBasic getPlugin() {
		return getPlugin(PvPBasic.class);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		this.saveDefaultConfig();

		kits.add(DEFAULT_KIT);
		kits.add(new Archer());
		kits.add(new Tank());
		kits.add(new Healer());
		kits.add(new Grandpa());
		kits.add(new Teleporter());
		kits.add(new Enchanter());

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Healer(), this);
		pm.registerEvents(new Teleporter(), this);

		pm.registerEvents(new KitListeners(), this);
		pm.registerEvents(new PlayerListeners(), this);
		pm.registerEvents(new ProtectionListeners(), this);
		pm.registerEvents(new WorldListeners(), this);

		pm.registerEvents(new KitsGUI(), this);

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