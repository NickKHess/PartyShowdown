package com.csg.showdown.rounds.entities;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import com.csg.showdown.entities.GamePlayer;

// Probably won't be used
@Deprecated
public final class RoundTeam {

	private String name;
	private ChatColor color;
	private List<GamePlayer> members;
	
	public RoundTeam(ChatColor color, String name, List<GamePlayer> members) {
		this.name = name;
		this.color = color;
		this.members = members;
	}
	
	public RoundTeam(ChatColor color, String name) {
		this.name = name;
		this.color = color;
		this.members = new ArrayList<>();
	}
	
	public RoundTeam(ChatColor color) {
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

	public List<GamePlayer> getMembers() {
		return members;
	}

	public void setMembers(List<GamePlayer> members) {
		this.members = members;
	}
	
	public void addMember(GamePlayer toAdd) {
		members.add(toAdd);
	}
	
	public void removeMember(GamePlayer toRemove) {
		members.remove(toRemove);
	}
	
}