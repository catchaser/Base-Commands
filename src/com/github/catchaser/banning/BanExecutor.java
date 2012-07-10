package com.github.catchaser.banning;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.catchaser.BaseCommands;

public class BanExecutor implements CommandExecutor{
	public static Logger logger = Logger.getLogger("Minecraft");
	public ListStore bannedPlayers;

	private BaseCommands plugin;
	public BanExecutor(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(commandLabel.equalsIgnoreCase("ban")) {
			if(p.hasPermission("BC.admin.ban") == false || p.hasPermission("BC.admin.*") == false || p.isOp() == true) {
				if(args.length >= 2 || args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Usage: /ban <player>");
					return true;
				}else{
				Player ban = p.getServer().getPlayer(args[0]);
				plugin.bannedPlayers.add(args [0]);
				if(ban != null) {
					ban.kickPlayer("You have been banned by: " + sender.getName());
				}
				plugin.bannedPlayers.save();
				p.sendMessage(ChatColor.RED + args[0] + " was banned");
				return true;
				}
			}else if(p.hasPermission("BC.admin.ban") == false || sender.hasPermission("BC.admin.*") == false) {
				p.sendMessage(ChatColor.RED + "You do not have permission");
			}
		}
		if(commandLabel.equalsIgnoreCase("unban")) {
			if(p.hasPermission("BC.admin.unban") == false || p.hasPermission("BC.admin.*") == false || p.isOp() == true) {
				if(args.length >= 2 || args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Usage: /unban <player>");
					return true;
				}else{
				@SuppressWarnings("unused")
				Player ban = p.getServer().getPlayer(args[0]);
				plugin.bannedPlayers.remove(args [0]);
				plugin.bannedPlayers.save();
				p.sendMessage(ChatColor.RED + args[0] + " was unbanned");
				return true;
			    }
			}else if(p.hasPermission("BC.admin.unban") == false || sender.hasPermission("BC.admin.*") == false) {
				p.sendMessage(ChatColor.RED + "You do not have permission");
			}
		}
		return true;
	}
}