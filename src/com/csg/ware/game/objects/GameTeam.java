package com.csg.ware.game.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;

public class GameTeam {

	private String name;
	private ChatColor color;
	private List<UUID> members;
	
	public GameTeam(String name, ChatColor color, List<UUID> members) {
		this.name = name;
		this.color = color;
		this.members = members;
	}
	
	public GameTeam(String name, ChatColor color) {
		this.name = name;
		this.color = color;
		this.members = new ArrayList<>();
	}
	
	public GameTeam(ChatColor color) {
		this.name = color.name();
		this.color = color;
		this.members = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ChatColor getColor() {
		return color;
	}

	public void setColor(ChatColor color) {
		this.color = color;
	}

	public List<UUID> getMembers() {
		return members;
	}

	public void setMembers(List<UUID> members) {
		this.members = members;
	}
	
	public void addMember(UUID toAdd) {
		members.add(toAdd);
	}
	
	public void removeMember(UUID toRemove) {
		members.remove(toRemove);
	}
	
}