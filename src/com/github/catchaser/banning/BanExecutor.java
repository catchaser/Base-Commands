package com.github.catchaser.banning;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.catchaser.BaseCommands;
import com.github.catchaser.listeners.BanStore;

public class BanExecutor implements CommandExecutor{
	public static Logger logger = Logger.getLogger("Minecraft");
	public BanStore bannedPlayers;

	private BaseCommands plugin;
	public BanExecutor(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(commandLabel.equalsIgnoreCase("ban")) {
			if(p.hasPermission("BC.admin.ban") || p.hasPermission("BC.admin.*")) {
				if(args.length >= 2 || args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Usage: /ban <player>");
					return true;
				}else{
				Player ban = p.getServer().getPlayer(args[0]);
				plugin.bannedPlayers.add(args [0]); // Adds player name to BC-banned-players.txt file
				if(ban != null) {
					ban.kickPlayer("You have been banned by: " + sender.getName()); // kicks the banned player from the server
				}
				plugin.bannedPlayers.save(); // saves the file
				logger.info(ChatColor.RED + p.getName() + "Has banned: " + ban);
				p.sendMessage(ChatColor.RED + args[0] + " was banned");
				return true;
				}
			}else if(!(p.hasPermission("BC.admin.ban") || p.hasPermission("BC.admin.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM"); //locates and reads the string
				for(String per : perm)
					p.sendMessage(per);
			}
		}
		if(commandLabel.equalsIgnoreCase("unban")) {
			if(p.hasPermission("BC.admin.unban") || p.hasPermission("BC.admin.*")) {
				if(args.length >= 2 || args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Usage: /unban <player>");
					return true;
				}else{
				Player ban = p.getServer().getPlayer(args[0]);
				plugin.bannedPlayers.remove(args [0]); //remove player from BC-banned-players.txt file
				plugin.bannedPlayers.save();//saves the file
				logger.info(ChatColor.GOLD +p.getName() + "Has unbanned: " + ban);
				p.sendMessage(ChatColor.RED + args[0] + " was unbanned");
				return true;
			    }
			}else if(!(p.hasPermission("BC.admin.unban") || sender.hasPermission("BC.admin.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM"); //locates and reads the string
				for(String per : perm)
					p.sendMessage(per);
			}
		}
		return true;
	}
}