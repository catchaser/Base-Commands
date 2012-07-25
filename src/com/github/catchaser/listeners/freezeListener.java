package com.github.catchaser.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.catchaser.BaseCommands;

public class freezeListener implements Listener {
	
	public static BaseCommands plugin;
	
	public freezeListener(BaseCommands instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent event) {
		if(plugin.freeze) {
			Player player = event.getPlayer();
			player.teleport(player);
		}
	}

}
