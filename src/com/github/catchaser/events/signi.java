package com.github.catchaser.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.github.catchaser.BaseCommands;

public class signi implements Listener{
	
	private BaseCommands plugin;
	public int item1;
	
	public signi(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSignCreate(SignChangeEvent event) {
		Player p = (Player) event.getPlayer();
		if(event.getLine(0).equalsIgnoreCase("[BC]")) {
			if(plugin.signif) {
				if(!(p.hasPermission("BC.admin.sign.create") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*"))) {
					event.setLine(0, ChatColor.DARK_RED + "!ERROR!");
					event.setLine(1, "you don't have");
					event.setLine(2, "permission");
					event.setLine(3, ChatColor.DARK_RED + "!ERROR!");
					return;
				}else if(p.hasPermission("BC.admin.sign.create") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*")) {
					if(event.getLine(1).equals("ITEMBANK")) {
						if(event.getLine(2).equals(event.getPlayer().getName())) {
							event.setLine(0, ChatColor.WHITE + "BaseCommands");
							event.setLine(1, ChatColor.DARK_AQUA + "ITEMBANK");
							event.setLine(2, event.getPlayer().getName());
						}else if(!(event.getLine(2).equals(event.getPlayer().getName()))) {
							event.setLine(0, ChatColor.DARK_RED + "!ERROR!");
							event.setLine(1, ChatColor.DARK_RED + "!ERROR!");
							event.setLine(2, ChatColor.DARK_RED + "!ERROR!");
							event.setLine(3, ChatColor.DARK_RED + "!ERROR!");
							p.sendMessage(ChatColor.DARK_RED + "Please put your player name at line 3!");
						}
					}else{
						
					}
				}
			}else if(!(plugin.signif)) {
				p.sendMessage(ChatColor.GOLD + "ITEMBANK Feature is Disabled!");
				event.getBlock().breakNaturally();
			}
		}
	}

}
