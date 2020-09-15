package com.csg.utils.position;

import org.bukkit.entity.Player;

public class SignLocationManager {

	private Player editor;
	private String name;
	
	public SignLocationManager(Player editor, String name) {
		this.editor = editor;
		this.name = name;
	}

	public Player getEditor() {
		return editor;
	}

	public void setEditor(Player editor) {
		this.editor = editor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}