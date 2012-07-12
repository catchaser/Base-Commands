package com.github.catchaser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import couk.Adamki11s.Extras.Colour.ExtrasColour;
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
		ExtrasColour eC = new ExtrasColour();
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
						if(TargetP != null) {
						TargetP.setHealth(20);
						TargetP.sendMessage(ChatColor.GOLD + "You were healed by: " + player.getDisplayName());
						player.sendMessage(ChatColor.GOLD + "You healed: " + TargetP.getDisplayName());
						}else if(TargetP == null) {
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
			if(player.hasPermission("BC.tp.*")|| player.hasPermission("BC.tp.2p")) {
				if(args.length == 0) {
					eC.sendMultiColouredMessage(player, "You must select a player");
					eC.sendColouredMessage(player, "Usage: /tp <player>");
					return true;
				}else if(args.length == 1) {
					Player targetPlayer = player.getServer().getPlayer(args[0]);
					  if(targetPlayer == null){
	                        eC.sendMultiColouredMessage(player, "Player is not online!");
	                    return true;
	                    }else if(targetPlayer != null){
                    Location loc = targetPlayer.getLocation();
                    player.teleport(loc);
                    player.sendMessage(ChatColor.GOLD + "You teleported to: " + "" + targetPlayer.getName());
                    return true;
	                    }
				}else if(args.length == 2) {
					if(player.hasPermission("BC.tp.p2p") || player.hasPermission("BC.tp.*")) {
					Player TP1 = player.getServer().getPlayer(args[0]);
					Player TP2 = player.getServer().getPlayer(args[1]);
					if(TP1 == null || TP2 == null) 
					{
						player.sendMessage(PREFIX  + ChatColor.GREEN  + " Ether one or both of he players are offline!");
						return true;
					}
					Location Loc = TP2.getLocation();
					TP1.teleport(Loc);
					TP1.sendMessage(ChatColor.BLUE + " You were teleported to:" + TP2.getName() + " by: " + player.getName());
					}else if(!(player.hasPermission("BC.tp.p2p") || player.hasPermission("BC.tp.*"))) {
						player.sendMessage(PERM);
					}
				}else if(args.length >= 3) {
					eC.sendColouredMessage(player, "Usage: /tp <player>");
				}
			}else if(!(player.hasPermission("BC.tp.*") || player.hasPermission("BC.tp.p2p"))) {
				player.sendMessage(PERM);
			}
		}
		if(commandLabel.equalsIgnoreCase("tphere")) {
			if(player.hasPermission("BC.tp.here") || player.hasPermission("BC.tp.*")) {
				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "You must select a player");
				}else if(args.length == 1) {
					Player TP = player.getServer().getPlayer(args[0]);
					if(TP == null) {
						player.sendMessage(ChatColor.GREEN + "Player not online!");
					}else if(TP != null) {
						Location loc = player.getLocation();
						TP.teleport(loc);
						TP.sendMessage(ChatColor.GOLD + "You were teleported to: " + player.getName());
						player.sendMessage(ChatColor.GOLD + "You teleported: " + TP.getName() + " to you!");
					}
				}else if(args.length == 2) {
					if(args[2] == "all") {
						player.sendMessage(PREFIX + " Not yet Supported");
					}
				}else if(args.length >= 3) {
					player.sendMessage(PREFIX + " Nope!");
				}
			}else if(!(player.hasPermission("BC.tp.here") || player.hasPermission("BC.tp.*"))) {
				player.sendMessage(PERM);
			}
		}
		if(commandLabel.equalsIgnoreCase("fly")) {
			if(player.hasPermission("BC.fight.fly") == true || player.hasPermission("BC.flight.*") == true) {
				if(args.length == 0) {
					if(player.getAllowFlight() == true) {
						player.sendMessage(ChatColor.GREEN + "Flying already Enabled");
					}else if(player.getAllowFlight() == false) {
						player.setAllowFlight(true);
						player.sendMessage(ChatColor.GOLD + "Flying now enabled!");
					}
				}else if(!(args.length == 0)) {
				player.sendMessage(ChatColor.RED + "Usage: /dfly");
				}
			}else if(player.hasPermission("BC.fight.fly") == false || player.hasPermission("BC.flight.*") == false) {
				player.sendMessage(PERM);
			}
		}
		if(commandLabel.equalsIgnoreCase("dfly")) {
			if(player.hasPermission("BC.flight.dfly") == true || player.hasPermission("BC.flight.*") == true) {
				if(args.length == 0) {
					if(player.getAllowFlight() == true) {
						player.setAllowFlight(false);
						player.sendMessage(ChatColor.GOLD + "Flying now disabled!");
					}else if(player.getAllowFlight() == false) {
						player.sendMessage(ChatColor.GREEN + "Flying already disabled");
					}
				}else if(!(args.length == 0)) {
					player.sendMessage(ChatColor.RED + "Usage: /dfly");
				}
		  }else if(player.hasPermission("BC.flight.dfly") == false || player.hasPermission("BC.flight.*") == false) {
			  player.sendMessage(PERM);
		  }
		}
		if(commandLabel.equalsIgnoreCase("rules")) {
			List<String>  rule = plugin.getConfig().getStringList("rules");
			for(String r : rule)
				player.sendMessage(r);
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