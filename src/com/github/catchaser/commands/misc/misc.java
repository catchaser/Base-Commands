package com.github.catchaser.commands.misc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.github.catchaser.BaseCommands;

public class misc implements CommandExecutor{
	public static final String PREFIX = ChatColor.GREEN + "[BaseCommands]" + ChatColor.WHITE;
	
	private BaseCommands plugin;
	
	public misc(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player) sender;
		}
		PluginDescriptionFile pdfFile = plugin.getDescription();
		if(commandLabel.equalsIgnoreCase("nickname")) {
			if(p == null) {
				if(args.length == 0) {
					sender.sendMessage(PREFIX + ChatColor.BLUE + " You must select a player and a nick name");
					sender.sendMessage(PREFIX + ChatColor.BLUE + " Usage: nickname <player> <nickname>");
					return true;
				}else if(args.length == 1){
					sender.sendMessage(ChatColor.BLUE + " You must select a player/nickname");
					sender.sendMessage(ChatColor.BLUE + " Usage: nickname <player> <nickname>");
					return true;
				}else if(args.length == 2) {
					Player TP = plugin.getServer().getPlayer(args[0]);
					TP.setDisplayName(args[1]);
					TP.sendMessage(ChatColor.GOLD + sender.getName() + " has set your nickname to: " + TP.getDisplayName());
					sender.sendMessage(ChatColor.GOLD + "You have set: " + TP.getName() + "'s nickname to: " + TP.getDisplayName());
					return true;
				}else if(args.length  >= 3) {
					sender.sendMessage("Usage: nickname <player> <nickname>");
					return true;
				}
			}else if(p != null) {
				if(p.hasPermission("BC.name.nick") || p.hasPermission("BC.*") || p.hasPermission("BC.name.*")) {
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
						TP.sendMessage(ChatColor.GOLD + p.getName() + " has set your nickname to: " + TP.getDisplayName());
						p.sendMessage(ChatColor.GOLD + "You have set: " + TP.getName() + "'s nickname to: " + TP.getDisplayName());
						return true;
					}else if(args.length  >= 3) {
						p.sendMessage("Usage: /nickname <player> <nickname>");
						return true;
					}
				}else if(!p.hasPermission("BC.name.nick") || p.hasPermission("BC.*") || p.hasPermission("BC.name.*")) {
					String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
				}
			}
		}
		if(commandLabel.equalsIgnoreCase("unnick")) {
			if(p == null) {
				if(args.length  == 0 || args.length >=2) {
					sender.sendMessage(PREFIX + ChatColor.BLUE + " Usage: unnick <player>");
				}else if(args.length == 1) {
					Player TP = plugin.getServer().getPlayer(args [0]);
					TP.getName();
					TP.setDisplayName(plugin.getServer().getPlayer(args [0]).getName());
					sender.sendMessage(PREFIX + ChatColor.GOLD + " Nick name removed from: " + ChatColor.GREEN + TP.getName());
					TP.sendMessage(ChatColor.GOLD + "Nickname removed by: " + ChatColor.GREEN + sender.getName());
				}
			}else if(p != null) {
				if(p.hasPermission("BC.name.unnick") || p.hasPermission("BC.name.*") || p.hasPermission("BC.*")) {
					if(args.length  == 0 || args.length >=2) {
						p.sendMessage(ChatColor.BLUE + "Usage: /unnick <player>");
					}else if(args.length == 1) {
						Player TP = plugin.getServer().getPlayer(args [0]);
						TP.getName();
						TP.setDisplayName(plugin.getServer().getPlayer(args [0]).getName());
						p.sendMessage(ChatColor.GOLD + "Nick name removed from: " + ChatColor.GREEN + TP.getName());
						TP.sendMessage(ChatColor.GOLD + "Nickname removed by: " + ChatColor.GREEN + p.getName());
					}
				}else if(!p.hasPermission("BC.name.nick") || p.hasPermission("BC.*")) {
					String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
				}
			}
		}
		if(commandLabel.equalsIgnoreCase("bcversion")) {
			if(p == null) {
				sender.sendMessage(PREFIX + " Plugin Version: " + pdfFile.getVersion());
				sender.sendMessage(PREFIX + " Bukkit Version: " + plugin.getServer().getVersion());
			}else if(p != null) {
				p.sendMessage(PREFIX + " Plugin Version: " + pdfFile.getVersion());
				p.sendMessage(PREFIX + " Bukkit Version: " + p.getServer().getVersion());
			}
		}
		if(commandLabel.equalsIgnoreCase("feed")) {
			if(p == null) {
				if(args.length  == 0) {
					sender.sendMessage(PREFIX + " Usage: feed <player>");
				}else if(args.length == 1) {
					Player TP = plugin.getServer().getPlayer(args[0]);
					if(TP != null) {
					TP.setFoodLevel(20);
					TP.sendMessage(ChatColor.GOLD + " Foodlevel restoredby: " + sender.getName());
					sender.sendMessage(PREFIX + ChatColor.GOLD + " you restored: " + TP.getName() + "'s food level");
					return true;
					}else if(TP == null) {
						sender.sendMessage(PREFIX + ChatColor.RED + " Player not online!");
					}
				}else if(args.length >= 2) {
					sender.sendMessage(PREFIX + ChatColor.YELLOW + " Usage: feed <player>");
					return true;
				}
			}else if(p != null) {
				if(p.hasPermission("BC.heal.feed") || p.hasPermission("BC.heal.*") || p.hasPermission("BC.*")) {
					if(args.length  == 0) {
						p.setFoodLevel(20);
						p.sendMessage(ChatColor.GOLD + " Food level restored");
						return true;
					}else if(args.length == 1) {
						Player TP = p.getServer().getPlayer(args[0]);
						if(TP != null) {
						TP.setFoodLevel(20);
						TP.sendMessage(ChatColor.GOLD + " Foodlevel restoredby: " + p.getName());
						p.sendMessage(ChatColor.GOLD + " you restored: " + TP.getName() + "'s food level");
						return true;
						}else if(TP == null) {
							p.sendMessage(ChatColor.RED + " Player not online!");
						}
					}else if(args.length >= 2) {
						p.sendMessage(ChatColor.YELLOW + "Usage: /feed <player> < > = optional for this command");
						return true;
					}
				}else if(!(p.hasPermission("BC.heal.feed") || p.hasPermission("BC.heal.*") || p.hasPermission("BC.*"))) {
					String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
				}
			}
		}
		if(commandLabel.equalsIgnoreCase("mute")) {
			if(p == null) {
				if(args.length  == 1) {
					Player TP = plugin.getServer().getPlayer(args[0]);
					if(TP != null) {
						if(plugin.mute) {
							sender.sendMessage(PREFIX + ChatColor.GOLD + " Player already muted!");
							sender.sendMessage(PREFIX + ChatColor.GOLD + " To unmute use /unmute <player>");
						}else if(!(plugin.mute)) {
							plugin.mute = true;
							TP.sendMessage(ChatColor.RED + "You have been muted by: " + sender.getName());
							sender.sendMessage(PREFIX + ChatColor.BLUE + " You muted: " + TP.getName());
						}
					}else if(TP == null) {
						sender.sendMessage(PREFIX + ChatColor.GREEN + " Player is not online!");
					}
				}else if(!(args.length == 1)) {
					sender.sendMessage(PREFIX + ChatColor.BLUE + " Usage: mute <player>");
				}
			}else if(p != null) {
				if(p.hasPermission("BC.admin.mute") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*")) {
					if(args.length  == 1) {
						Player TP = plugin.getServer().getPlayer(args[0]);
						if(TP != null) {
							if(plugin.mute) {
								p.sendMessage(ChatColor.GOLD + "Player already muted!");
								p.sendMessage(ChatColor.GOLD + "To unmute use /unmute <player>");
							}else if(!(plugin.mute)) {
								plugin.mute = true;
								TP.sendMessage(ChatColor.RED + "You have been muted by: " + p.getName());
								p.sendMessage(ChatColor.BLUE + "You muted: " + TP.getName());
							}
						}else if(TP == null) {
							p.sendMessage(ChatColor.GREEN + "Player is not online!");
						}
					}else if(!(args.length == 1)) {
						p.sendMessage(ChatColor.BLUE + "Usage: /mute <player>");
					}
				}else if(!(p.hasPermission("BC.admin.mute") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*"))) {
					String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
				}
			}
		}
		if(commandLabel.equalsIgnoreCase("unmute")) {
			if(p == null) {
				if(args.length == 1) {
					Player TP = plugin.getServer().getPlayer(args[0]);
					if(TP != null) {
						if(plugin.mute) {
							plugin.mute = false;
							TP.sendMessage(ChatColor.GREEN + "You have been unmuted by: " + sender.getName());
							sender.sendMessage(PREFIX + ChatColor.BLUE + " You unmuted: " + TP.getName());
						}else if(!(plugin.mute)) {
							sender.sendMessage(PREFIX + ChatColor.GOLD + " Player is not muted!");
							sender.sendMessage(PREFIX + ChatColor.GOLD + "To mute a player use mute <player>");
						}
					}else if(TP == null) {
						sender.sendMessage(PREFIX + ChatColor.BLUE + " Player is offline");
					}
				}else if(!(args.length == 1)) {
					sender.sendMessage(PREFIX + ChatColor.BLUE + " Usage: unmute <player>");
				}
			}else if(p != null) {
				if(p.hasPermission("BC.admin.unmute") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*")) {
					if(args.length == 1) {
						Player TP = plugin.getServer().getPlayer(args[0]);
						if(TP != null) {
							if(plugin.mute) {
								plugin.mute = false;
								TP.sendMessage(ChatColor.GREEN + "You have been unmuted by: " + p.getName());
								p.sendMessage(ChatColor.GREEN + "You unmuted: " + TP.getName());
							}else if(!(plugin.mute)) {
								p.sendMessage(ChatColor.GOLD + "Player is not muted!");
								p.sendMessage(ChatColor.GOLD + "To mute a player use /mute <player>");
							}
						}else if(TP == null) {
							p.sendMessage(ChatColor.GREEN + "Player is offline");
						}
					}else if(!(args.length == 1)) {
						p.sendMessage(ChatColor.BLUE + "Usage: /unmute <player>");
					}
				}else if(!(p.hasPermission("BC.admin.unmute") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*"))) {
					String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
				}
			}
		}
		return false;
	}
}