package com.github.catchaser.misc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.github.catchaser.BaseCommands;

public class misc implements CommandExecutor{
	public static final String PERM = ChatColor.RED + "You do not have that permission!";
	public static final String PREFIX = ChatColor.GREEN + "[BaseCommands]" + ChatColor.WHITE;
	
	private BaseCommands plugin;
	
	public misc(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		PluginDescriptionFile pdfFile = plugin.getDescription();
		if(commandLabel.equalsIgnoreCase("nickname")) {
			if(p.hasPermission("BC.name.nick")) {
				if(args.length == 0) {
					p.sendMessage(ChatColor.GREEN + "You must select a player and a nick name");
					p.sendMessage(ChatColor.BLUE + "Usage: /nickname <player> <nickname>");
					return true;
				}else if(args.length == 1){
					p.sendMessage(ChatColor.GREEN + "You must select a player/nickname");
					p.sendMessage(ChatColor.BLUE + "Usage: /nickname <player> <nickname>");
					return true;
				}else if(args.length == 2) {
					Player TP = p.getServer().getPlayer(args[0]);
					TP.setDisplayName(args[1]);
					TP.setPlayerListName(args[1]);
					TP.sendMessage(ChatColor.GOLD + p.getName() + " has set your nickname to: " + TP.getDisplayName());
					p.sendMessage(ChatColor.GOLD + "You have set: " + TP.getName() + "'s nickname to: " + TP.getDisplayName());
					return true;
				}else if(args.length  >= 3) {
					p.sendMessage("Usage: /nickname <player> <nickname>");
					return true;
				}
			}else if(!p.hasPermission("BC.name.nick")) {
				p.sendMessage(PERM);
				return true;
			}
		}
		if(commandLabel.equalsIgnoreCase("bcversion")) {
			p.sendMessage(PREFIX + " Plugin Version: " + pdfFile.getVersion());
			p.sendMessage(PREFIX + " Bukkit Version: " + p.getServer().getVersion());
		}
		if(commandLabel.equalsIgnoreCase("feed")) {
			if(p.hasPermission("BC.heal.feed") || p.hasPermission("BC.heal.*")) {
				if(args.length  == 0) {
					p.setFoodLevel(20);
					p.sendMessage(PREFIX + ChatColor.GOLD + " Food level restored");
					return true;
				}else if(args.length == 1) {
					Player TP = p.getServer().getPlayer(args[0]);
					if(TP != null) {
					TP.setFoodLevel(20);
					TP.sendMessage(PREFIX + ChatColor.GOLD + " Foodlevel restoredby: " + p.getName());
					p.sendMessage(PREFIX + ChatColor.GOLD + " you restored: " + TP.getName() + "'s food level");
					return true;
					}else if(TP == null) {
						p.sendMessage(PREFIX + ChatColor.RED + " Player not online!");
					}
				}else if(args.length >= 2) {
					p.sendMessage(PREFIX + ChatColor.YELLOW + "Usage: /feed <player> < > = optional for this command");
					return true;
				}
			}else if(!(p.hasPermission("BC.heal.feed") || p.hasPermission("BC.heal.*"))) {
				p.sendMessage(PERM);
				return true;
			}
		}
		return false;
	}

}
