package com.csg.utils.position;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

public class SignLocationManager implements Listener {
	
	public static ArrayList<Player> editorList = new ArrayList<Player>();

	private Player editor;
	private String name;
	private int currentLine;
	
	private File dir = new File("plugins/SignLocations/");
	private File file = new File(dir, "locations");
	
	public SignLocationManager(Player editor, String name) {
		this.editor = editor;
		this.name = name;
		this.currentLine = checkIfExists();
		
		editorList.add(editor);
	}
	
	@EventHandler
	public void onPlaceSign(BlockPlaceEvent e) {
		if(!e.getPlayer().equals(this.editor))
			return;
		
		Player player = e.getPlayer();
		
		if(e.getBlock().getType() != Material.OAK_SIGN)
			return;
		
		player.sendMessage("Get sign");
		
		Sign sign = (Sign) e.getBlock().getState();
		
		player.sendMessage("Got sign");
		
		sign.setLine(0, "[Location]");
		
		//if(!sign.getLine(0).equalsIgnoreCase("[Location]"))
			//return;
		
		player.sendMessage("Placed sign location");
		//updateFile(sign.getLocation(), sign.getLine(1));
	}
	
	@EventHandler
	public void onSignEdit(SignChangeEvent e) {
		if(!e.getPlayer().equals(this.editor))
			return;
		
		Sign sign = (Sign) e.getBlock().getState();
		
		if(!e.getLine(0).equalsIgnoreCase("[Location]"))
		{
			e.setLine(0, "[Location]");
			e.getBlock().getState().update();
		}
		
		updateFile(sign.getLocation(), e.getLines()[1]);
	}
	
	public int appendFile(int line) {
		editor.sendMessage("Appending new file " + line);
		try {
			//FileWriter fileWriter = new FileWriter(file);
			/*
			ArrayList<String> lines = new ArrayList<String>(Files.readAllLines(file.toPath()));
			editor.sendMessage("lines: " + Arrays.toString(lines.toArray()) + " is empty? " + lines.isEmpty());
			String toWrite = "";
			
			if(!lines.isEmpty())
				toWrite += lines.get(lines.size() - 1) + "\n";*/
			
			String toWrite = "";
			
			toWrite = "[" + name + "{}]";
			
			editor.sendMessage("Writing: " + toWrite);
			
			Files.write(file.toPath(), toWrite.getBytes(), StandardOpenOption.APPEND);
			
			//fileWriter.write(toWrite);
			//fileWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return line;
	}
	
	public void updateFile(Location loc, String cmd) {
		editor.sendMessage("Update file");
		try {
			Scanner fileRead = new Scanner(file);
			String line = fileRead.nextLine();
			
			//String toWrite = "[" + name + "{" + "(" + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "," + cmd + "}]";
			
			ArrayList<String> lines = new ArrayList<String>(Files.readAllLines(file.toPath()));
			editor.sendMessage("lines: " + Arrays.toString(lines.toArray()));
			editor.sendMessage("currentLine: " + currentLine);
			
			for(int i = 0; i < currentLine; i++)
				line = fileRead.nextLine();
			
			String toWrite = line;
			
			editor.sendMessage("fileRead: " + line);
			
			for(int i = 0; i < lines.size(); i++)
				if(lines.get(i).equalsIgnoreCase(line))
				{
					// Checking if this is empty or not
					if(line.toCharArray()[line.indexOf("{") + 1] == '}')
						toWrite = toWrite.substring(0, toWrite.indexOf("{") + 1);
					else
						toWrite = toWrite.substring(0, toWrite.lastIndexOf(";") + 1);
					
					toWrite += "(" + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "," + cmd + ");}]";
					lines.set(i, toWrite);
				}
			//fileWriter.
			
			//String toWrite = "[" + name + "{" + "(" + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "," + cmd + "}]";
			
			fileRead.close();
			Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int checkIfExists() {
		if(file.length() == 0)
		{
			editor.sendMessage("File empty as hell");
			appendFile(0);
			return -1;
		}
		
		int found = 0;
		editor.sendMessage("Checking if group exists");
		
		try {
			Scanner fileRead = new Scanner(file);
			String line = fileRead.nextLine();
			boolean loop = true;
			
			editor.sendMessage("First line: " + line);
			
			do {
				editor.sendMessage("Has next line");
				String lineName = line.substring(line.indexOf("[") + 1, line.indexOf("{"));
				editor.sendMessage(lineName);
				editor.sendMessage(line);
				
				if(name.equalsIgnoreCase(lineName))
				{
					editor.sendMessage("Line found " + found);
					return found;
				}
				
				found++;
				loop = fileRead.hasNextLine();
				line = loop ? fileRead.nextLine() : null;
			} while(loop);
				
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return appendFile(found);
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