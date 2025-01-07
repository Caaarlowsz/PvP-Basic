package com.github.caaarlowsz.pvp;

import org.bukkit.entity.Player;

public class Kit {

	private final String name, description;
	private final Stack icon;

	public Kit(String name, String description, Stack icon) {
		this.name = name;
		this.description = description;
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Stack getIcon() {
		return this.icon;
	}

	public void giveItems(Player player) {
	}
}