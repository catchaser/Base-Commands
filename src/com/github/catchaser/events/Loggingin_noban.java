package com.github.catchaser.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.catchaser.BaseCommands;

public class Loggingin_noban implements Listener{
	
	private BaseCommands plugin;
	
	public Loggingin_noban(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void playerJoin(PlayerJoinEvent event) {
    	String message = plugin.getConfig().getString("MOTD");
    	message = ChatColor.translateAlternateColorCodes('&', message);
    	message = ChatColor.translateAlternateColorCodes('$', message);
    	message = ChatColor.translateAlternateColorCodes('%', message);
    	event.getPlayer().sendMessage(message);
    }

}
