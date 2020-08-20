package com.csg.ware.game.games;

import java.util.UUID;
import org.bukkit.entity.Player;
import com.csg.ware.Ware;
import com.csg.ware.game.Game;

public class GameSnowballFight extends Game {

	int[] snowballSlots = new int[] {0, 1, 2};

	public GameSnowballFight() {
		super("Snowball Fight", "Eliminate other players using snowballs!");
	}

	@Override
	public void performGameAction() {
		for(UUID uuid : Ware.getPlayers())
			for(int slot : snowballSlots)
				
	}

}