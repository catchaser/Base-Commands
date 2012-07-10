package com.github.catchaser;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import couk.Adamki11s.Extras.Events.*;

@SuppressWarnings("unused")
public class BaseCommandsCommandExecutor extends JavaPlugin implements CommandExecutor {
	public static final String PREFIX = ChatColor.GREEN + "[BaseCommands]" + ChatColor.WHITE;
	ExtrasEvents eevent = new ExtrasEvents();
	public static final String PERM = ChatColor.RED + "You do not have that permission!";

	private BaseCommands plugin;
	 
	public BaseCommandsCommandExecutor(BaseCommands plugin) {
		this.plugin = plugin;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("heal")) {
				if(args.length == 0) {
					if(player.hasPermission("BC.heal.self") || player.hasPermission("BC.heal.*")) {
					player.setHealth(20);
					player.sendMessage(ChatColor.GOLD + "Healed");
					}else if(player.hasPermission("BC.heal.self") == false || player.hasPermission("BC.heal.*")== false) {
						player.sendMessage(PERM);
					}
				}else if(args.length == 1) {
					if(player.hasPermission("BC.heal.other") || player.hasPermission("BC.heal.*")) {
						Player TargetP = getServer().getPlayer(args [1]);
						if(TargetP.isOnline() == true) {
						TargetP.setHealth(20);
						TargetP.sendMessage(ChatColor.GOLD + "You were healed by: " + player.getDisplayName());
						player.sendMessage(ChatColor.GOLD + "You healed: " + TargetP.getDisplayName());
						}else if(TargetP.isOnline() == false) {
							player.sendMessage(ChatColor.GREEN + "Player is not online");
						}
					}else if(player.hasPermission("BC.heal.other") == false || player.hasPermission("BC.heal.*")) {
						player.sendMessage(PERM);
					}
				}else if(args.length >= 2) {
					player.sendMessage(ChatColor.GREEN + "Please only do one player at a time");
				}
		}
		if(commandLabel.equalsIgnoreCase("tp")) {
			if(player.hasPermission("BC.tp.*") == true || player.hasPermission("BC.tp.p2p") == true) {
				if((args.length < 0)) {
					player.sendMessage(ChatColor.GREEN + "You must select a player to TP to!");
				}else if(args.length == 0) {
					Player TargetP = getServer().getPlayer(args [1]);
					Location location = TargetP.getLocation();
					player.teleport(location);
				}else if(args.length >= 1) {
					Player TargetP1 = getServer().getPlayer(args[0]);
					Player TargetP2 = getServer().getPlayer(args[1]);
					player.sendMessage(ChatColor.WHITE + "Under Development (Teleport player to Player)");
					
				}  
			}else if(player.hasPermission("BC.tp.*") == false || player.hasPermission("BC.tp.p2p") == true) {
				player.sendMessage(PERM);
			}
		}
		if(commandLabel.equalsIgnoreCase("fly")) {
			if(player.hasPermission("BC.fight.fly") == true || player.hasPermission("BC.flight.*") == true) {
				player.setAllowFlight(true);
				player.sendMessage(ChatColor.GOLD + "Flying now enabled!");
			}else if(player.hasPermission("BC.fight.fly") == false || player.hasPermission("BC.flight.*") == false) {
				player.sendMessage(PERM);
			}
		}
		if(commandLabel.equalsIgnoreCase("dfly")) {
			if(player.hasPermission("BC.flight.dfly") == true || player.hasPermission("BC.flight.*") == true) {
			if(player.getAllowFlight() == true) {
				player.setAllowFlight(false);
				player.sendMessage(ChatColor.GOLD + "Flying now disabled!");
			}else if(player.getAllowFlight() == false) {
				player.sendMessage(ChatColor.GREEN + "Flying already disabled");
			}
		  }else if(player.hasPermission("BC.flight.dfly") == false || player.hasPermission("BC.flight.*") == false) {
			  player.sendMessage(PERM);
		  }
		}
		if(commandLabel.equalsIgnoreCase("rules")) {
			java.util.List<String>  rule = getConfig().getStringList("rules");
            for (String r : rule)
            	player.sendMessage(r); 
            return false;
		}
		if(commandLabel.equalsIgnoreCase("whoiso")) {
			if(player.hasPermission("BC.who.iso") == true) {
            String allPlayers = "";
            for(int i = 0; i < plugin.getServer().getOnlinePlayers().length; i++)
            {
                String playerName = plugin.getServer().getOnlinePlayers()[i].getDisplayName();
                allPlayers += playerName;
                if(i != plugin.getServer().getOnlinePlayers().length - 1)
                    allPlayers += ", ";
            }

            // Print all
            player.sendMessage(ChatColor.GRAY + "Online Players: (" + plugin.getServer().getOnlinePlayers().length + ")");
            player.sendMessage(allPlayers);
			}else if(player.hasPermission("BC.who.iso") == false) {
				player.sendMessage(PERM);
			}
		}
		return false;
	}
}