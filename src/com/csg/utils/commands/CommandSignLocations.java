package com.csg.utils.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import com.csg.showdown.Showdown;
import com.csg.utils.position.SignLocationManager;

@Deprecated
public class CommandSignLocations implements CommandExecutor {
	
	private static ArrayList<HashMap<String, ArrayList<Location>>> signGroupList = new ArrayList<HashMap<String, ArrayList<Location>>>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("csa.signlocations")) {
				// 
				if(args.length >= 1) {
					//File file = new File("\\plugins\\SignLocations\\");
					// TODO: DO IDEAS IN COMMENTS
					// IMPLEMENT THEM IN SignLocationManager
					if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("create")
							|| args[0].equalsIgnoreCase("edit") || args[0].equalsIgnoreCase("modify")) {
						Bukkit.getServer().getPluginManager().registerEvents(new SignLocationManager(player, args[1]), Showdown.getPlugin());
						// Instantiate a SignGroup
						// Add it to a list of SignGroup
						// SignGroup class - stores sign locations, exports to config
						// Begin editing the SignGroup (place Player and SignGroup in static HashMap??)
						// Should multiple people be able to edit?
					}
					else if(args[0].equalsIgnoreCase("confirm") || args[0].equalsIgnoreCase("finish")) {
						player.sendMessage("Listener list: " + Arrays.toString(HandlerList.getRegisteredListeners(Showdown.getPlugin()).toArray()));
						// Check if player is editing
						// Finalize everything, make sure it exports to config
						// Delete dem signs
						// Remove Player from HashMap
					}
					else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) {
						// Check if SignGroup exists
						// If editing, delete signs
						// Remove from config
						// ???
						// Profit
					}
					else if(args[0].equalsIgnoreCase("test")) {
						
						Bukkit.getServer().getPluginManager().registerEvents(new SignLocationManager(player, args[1]), Showdown.getPlugin());
						
						/*
						//HashMap<String, ArrayList<Location>> test = new HashMap<String,  ArrayList<Location>>();
						ArrayList<Location> test_Locations = new ArrayList<Location>();
						
						test_Locations.add(player.getLocation());
						test_Locations.add(player.getLocation().add(1, 0, 0));
						test_Locations.add(player.getLocation().add(-1, 0, 0));
						test_Locations.add(player.getLocation().add(0, 1, 0));
						test_Locations.add(player.getLocation().add(0, -1, 0));
						test_Locations.add(player.getLocation().add(0, 0, 1));
						test_Locations.add(player.getLocation().add(0, 0, -1));
						
						//test.put(args[1], test_Locations);
						
						
						File dir = new File("plugins/SignLocations/");
						
						if(dir.mkdir())
							Bukkit.broadcastMessage(dir.getName() + " was generated in " + dir.getPath());
						else
							Bukkit.broadcastMessage(dir.getName() + " already exists in " + dir.getAbsolutePath());
						
						File file = new File(dir,"locations");
						
						try 
						{
							if(file.createNewFile())
								Bukkit.broadcastMessage(file.getName() + " was generated in " + file.getPath());
							else
								Bukkit.broadcastMessage(file.getName() + " already exists in " + file.getPath());
							
							FileWriter fileWriter = new FileWriter(file);
							
							String toWrite = "[" + args[1] + "{";
							
							for(Location l : test_Locations)
							{
								toWrite += "(" + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ", spawnZombie);";
							}
							
							toWrite += "}]";
							fileWriter.write(toWrite);
							fileWriter.close();
							
							
							Scanner fileRead = new Scanner(file);
							String line = fileRead.nextLine();
							
							boolean keepGoing = true;
							
							while(keepGoing)
							{
								String focus = line.substring(line.indexOf("("), line.indexOf(";"));
								
								int x = Integer.parseInt(focus.substring(focus.indexOf("(") + 1, focus.indexOf(",")));
								focus = focus.substring(focus.indexOf(",") + 1);
								
								int y = Integer.parseInt(focus.substring(0, focus.indexOf(",")));
								focus = focus.substring(focus.indexOf(",") + 1);
								
								int z = Integer.parseInt(focus.substring(0, focus.indexOf(",")));
								focus = focus.substring(focus.indexOf(",") + 1);
								
								String cmd = focus.substring(0, focus.indexOf(")"));
								
								Location loc = new Location(player.getWorld(), x, y, z);
								
								Block b = player.getWorld().getBlockAt(loc);
								b.setType(Material.OAK_SIGN);
								
								Sign sign = (Sign) b.getState();
								
								sign.setLine(0, "[Location]");
								sign.setLine(1, cmd);
								sign.update();
								
								line = line.substring(line.indexOf(";") + 1, line.length());
								Bukkit.broadcastMessage("Line: " + line);
								
								if(line.indexOf("}") == 0)
									
									keepGoing = false;
							}
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}*/
						
						
					}
				}
			}
			else
				player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
				
		}
		return true;
	}

}
