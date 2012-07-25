package com.github.catchaser.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.catchaser.BaseCommands;

public class BCC3 implements CommandExecutor{
	
	private BaseCommands plugin;
	public static String name;
	
	public BCC3(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(commandLabel.equalsIgnoreCase("msg")) {
			if(p.hasPermission("BC.who.msg") || p.hasPermission("BC.who.*")) { // checks if the players has the permissions
				if((args.length == 0) || args.length == 1) {
					p.sendMessage(ChatColor.BLUE + "Usage: /msg <player> <msg>"); // checks if they used the command right
				}else if(args.length >= 2 ) {
					Player TP = plugin.getServer().getPlayer(args[0]);
					if(TP == null || (sender instanceof Player && !((Player) sender).canSee(TP))) {
						p.sendMessage(ChatColor.RED + "Player is not online!");
						return true;
					}else {
						 StringBuilder message = new StringBuilder();	     
					     for (int i = 1; i < args.length; i++) {
			      		 if (i > 1) message.append(" ");
		         		 message.append(args[i]);
			             }			 
				         String result = ChatColor.DARK_PURPLE + "From: " + ChatColor.WHITE + p.getName() + ": " + ChatColor.DARK_PURPLE + message; 
			             TP.sendMessage(result);
						 p.sendMessage(ChatColor.GOLD + "Message sent!");
							        return true;
				    }
			    
				}
				
			}else if(!(p.hasPermission("BC.who.msg") || p.hasPermission("BC.who.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM");
				for(String per : perm)
					p.sendMessage(per);
				return true;
			}
		}
		if(commandLabel.equalsIgnoreCase("freeze")) {
			if(p.hasPermission("BC.admin.freeze") || p.hasPermission("BC.admin.*")) {
				if(args.length == 0 || args.length >= 2) {
					p.sendMessage(ChatColor.BLUE + "Usage: /freeze <player>");
				}else if(args.length == 1) {
					Player TP = plugin.getServer().getPlayer(args[0]);
					name = args[0];
					if(TP == null) {
						p.sendMessage(ChatColor.GREEN + "Player not online!");
					}else if(TP != null) {
						if(plugin.freeze) {
							p.sendMessage(ChatColor.RED + "Player already frozen!");
						}else if(!(plugin.freeze)) {
							plugin.freeze = true;
							TP.sendMessage(ChatColor.GOLD + "You have been frozen by: " + p.getName());
						}
					}
				}
			}else if(!(p.hasPermission("BC.admin.freeze") || p.hasPermission("BC.admin.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM");
				for(String per : perm)
					p.sendMessage(per);
				return true;
			}
		}
		if(commandLabel.equalsIgnoreCase("unfreeze")) {
			if(p.hasPermission("BC.admin.unfreeze") || p.hasPermission("BC.admin.*")) {
				if(args.length == 0 || args.length >= 2) {
					p.sendMessage(ChatColor.BLUE + "Usage: /unfreeze <player>");
				}else if(args.length == 1) {
					Player TP = plugin.getServer().getPlayer(args[0]);
					if(TP == null) {
						p.sendMessage(ChatColor.GREEN + "Player not online!");
					}else if(TP != null) {
						if(plugin.freeze) {
							plugin.freeze = false;
							TP.sendMessage(ChatColor.GREEN + "You have been unfrozened!");
							p.sendMessage(ChatColor.GOLD + "You unfroze: " + TP.getName());
						}else if(!(plugin.freeze)) {
							p.sendMessage(ChatColor.RED + "Player was not forzen!");
						}
					}
				}
			}else if(!(p.hasPermission("BC.admin.freeze") || p.hasPermission("BC.admin.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM");
				for(String per : perm)
					p.sendMessage(per);
				return true;
			}
		}
		return false;
	}

}
