package com.github.catchaser.events;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.catchaser.BaseCommands;

public class joining implements Listener{
	
	private BaseCommands plugin;
	
	public joining(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void playerJoin(PlayerJoinEvent event) {
    	String message = plugin.getConfig().getString("MOTD");
    	message = ChatColor.translateAlternateColorCodes('&', message);
    	message = ChatColor.translateAlternateColorCodes('$', message);
    	message = ChatColor.translateAlternateColorCodes('%', message);
    	event.getPlayer().sendMessage(message);
    	if(plugin.passwod) {
    		plugin.freeze = true;
    		plugin.mute = true;
    		event.getPlayer().sendMessage(ChatColor.GOLD + "This server has password enabled!");
    		if(new File("plugins/BaseCommands/passwds/" + event.getPlayer().getName()).exists()) {
    			event.getPlayer().sendMessage(ChatColor.GOLD + "Please enter your password with /passwd <password>");
    		}else if(!(new File("plugins/BaseCommands/passwds/" + event.getPlayer().getName()).exists())) {
    			event.getPlayer().sendMessage(ChatColor.GOLD + "Please set your password: /setpasswd <password>");
    		}
    	}
    }
}
