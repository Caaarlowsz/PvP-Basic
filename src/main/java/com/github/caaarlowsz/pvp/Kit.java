package com.github.caaarlowsz.pvp;

import org.bukkit.entity.Player;

public class Kit {

	private final String name;
	private String description;
	private final Stack icon;

	public Kit(String name, Stack icon) {
		this(name, new String(), icon);
	}

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

	public void setDescription(String description) {
		this.description = description;
	}

	public Stack getIcon() {
		return this.icon;
	}

	public void giveItems(Player player) {
	}
}