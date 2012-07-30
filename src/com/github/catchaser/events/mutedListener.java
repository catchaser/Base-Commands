package com.github.catchaser.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import com.github.catchaser.BaseCommands;

public class mutedListener implements Listener{
	
	public static BaseCommands plugin;
	
	public mutedListener(BaseCommands instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerChat(PlayerChatEvent event) {
		if(plugin.mute) {
			Player p = event.getPlayer();
			event.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You have been muted!");
		}
	}
	
}
