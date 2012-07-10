package com.github.catchaser.banning;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import com.github.catchaser.BaseCommands;

public class BanLogging implements Listener{
	
	private BaseCommands plugin;
	
	public BanLogging(BaseCommands plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerLogin(PlayerLoginEvent event) {
		String playerName = event.getPlayer().getName();
		
		if(plugin.bannedPlayers.contains(playerName)) {
			event.setKickMessage("You have been banned");
			event.setResult(Result.KICK_BANNED);
		}
	}

}