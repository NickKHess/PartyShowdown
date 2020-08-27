package com.csg.ware.rounds.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import com.csg.ware.entities.GamePlayer;
import com.csg.ware.rounds.objects.RoundTeam;

// Probably won't be used
@Deprecated
public abstract class TeamRound extends Round {

	List<RoundTeam> teams = new ArrayList<>();

	/**
	 * <i>TeamRound</i>
	 * <br>
	 * An abstract class for team minigame {@link Round}s 
	 * 
	 * @param name - The name of the game
	 * @param description - The description
	 * @param gameLength - The length of time, in seconds, which the game takes
	 * @param listener - The Event Manager to use
	 * @param numTeams - The number of teams (1-indexed)
	 */
	public TeamRound(String name, String description, int gameLength, Listener listener, int numTeams) {
		super(description, description, gameLength, listener);

		switch(numTeams) {
		case 1:
			teams.add(new RoundTeam(ChatColor.RED));
		case 2:
			teams.add(new RoundTeam(ChatColor.BLUE));
		case 3:
			teams.add(new RoundTeam(ChatColor.YELLOW));
		case 4:
			teams.add(new RoundTeam(ChatColor.GREEN));
			break;
		}
	}
	
	/**
	 * <i>assignTeams</i>
	 * <br>
	 * Assigns players to teams, (usually) balanced
	 * 
	 * @param players - The list of players to assign to teams
	 */
	public void assignTeams(List<GamePlayer> players) {
		// Clone and shuffle the list to randomize placement
		List<GamePlayer> shuffle = new ArrayList<>(players);
		Collections.shuffle(shuffle);
		
		// To separate players into (hopefully) evenly-sized teams
		int size = shuffle.size() / teams.size();
		
		// Loop over players and assign teams
		for(RoundTeam team : teams) {
			for(int i = 0; i < size; i++) {
				team.addMember(shuffle.get(i));
			}
			// Remove the first four entries
			// Avoided doing this above because concurrent modification is bad
			// This might error out once it runs out of players, we'll see
			// TODO: Make the teams balance out
			shuffle = shuffle.subList(size - 1, shuffle.size());
		}
				
	}
 
}