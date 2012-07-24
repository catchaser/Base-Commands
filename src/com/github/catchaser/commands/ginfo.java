package com.github.catchaser.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.BaseCommands;

import couk.Adamki11s.Extras.Player.ExtrasPlayer;
import couk.Adamki11s.Extras.Colour.*;

public class ginfo extends JavaPlugin implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private BaseCommands plugin;
	 
	public ginfo(BaseCommands plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		ExtrasPlayer extrasPlayer = new ExtrasPlayer();
		@SuppressWarnings("unused")
		ExtrasColour extrasColour = new ExtrasColour();
		if(commandLabel.equalsIgnoreCase("ginfo")) {
			if(player.hasPermission("BC.ginfo.1") || player.hasPermission("BC.ginfo.*")) {
				if(args.length == 0) {
					player.sendMessage(ChatColor.GOLD + "Name: " + player.getDisplayName());
					player.sendMessage(ChatColor.GOLD + "OP: True");
					player.sendMessage(ChatColor.GOLD + "IP: " + player.getAddress());
					player.sendMessage(ChatColor.GOLD + "Flying: " + player.getAllowFlight());
					player.sendMessage(ChatColor.GOLD + "Level: " +player.getLevel());
					player.sendMessage(ChatColor.GOLD + "World: " + player.getWorld().getName());
					player.sendMessage(ChatColor.GOLD + "Potion Effect: " + player.getActivePotionEffects());
					player.sendMessage(ChatColor.GOLD + "Minutes Lived: " + extrasPlayer.getMinutesLived(player));
				}else if(args.length == 1) {
					Player TargetP = getServer().getPlayer(args [1]);
					player.sendMessage(ChatColor.GOLD + "Name: " + TargetP.getDisplayName());
					player.sendMessage(ChatColor.GOLD + "OP: True");
					player.sendMessage(ChatColor.GOLD + "IP: " + TargetP.getAddress());
					player.sendMessage(ChatColor.GOLD + "Flying: " + TargetP.getAllowFlight());
					player.sendMessage(ChatColor.GOLD + "Level: " + TargetP.getLevel());
					player.sendMessage(ChatColor.GOLD + "World: " + TargetP.getWorld().getName());
					player.sendMessage(ChatColor.GOLD + "Potion Effect: " + TargetP.getActivePotionEffects());
					player.sendMessage(ChatColor.GOLD + "Minutes Lived: " + extrasPlayer.getMinutesLived(TargetP));
				}else if(args.length >= 2) {
					player.sendMessage(ChatColor.GREEN + "Please only do one player at a time!");
				}
			}else if(!(player.hasPermission("BC.ginfo.1") || player.hasPermission("BC.ginfo.*"))) {
				if(args.length == 0) {
					player.sendMessage(ChatColor.GOLD + "Name: " + player.getDisplayName());
					player.sendMessage(ChatColor.GOLD + "OP: False");
					player.sendMessage(ChatColor.GOLD + "IP: " + ChatColor.MAGIC + player.getAddress());
					player.sendMessage(ChatColor.GOLD + "Flying: " + player.getAllowFlight());
					player.sendMessage(ChatColor.GOLD + "Level: " +player.getLevel());
					player.sendMessage(ChatColor.GOLD + "World: " + player.getWorld().getName());
					player.sendMessage(ChatColor.GOLD + "Potion Effect: " + player.getActivePotionEffects());
					player.sendMessage(ChatColor.GOLD + "Minutes Lived: " + extrasPlayer.getMinutesLived(player));
				}else if(args.length >= 1) {
					player.sendMessage(ChatColor.RED + "You only have permission to look at your own info");
				}
			}
		}
		if(commandLabel.equalsIgnoreCase("ginfo2")) {
			if(player.hasPermission("BC.ginfo.2") || player.hasPermission("BC.ginfo.*")) {
				if(args.length  == 0){
					player.sendMessage(ChatColor.GOLD + "Hour's lived: " + extrasPlayer.getHoursLived(player));
					player.sendMessage(ChatColor.GOLD + "player is under water: " + extrasPlayer.isUnderWater(player));
					player.sendMessage(ChatColor.GOLD + "player last killed " + player.getDisplayName() + ": " + player.getKiller());
				}else if(args.length == 1) {
					Player TargetP = getServer().getPlayer(args [1]);
					player.sendMessage(ChatColor.GOLD + "Hour's lived: " + extrasPlayer.getHoursLived(TargetP));
					player.sendMessage(ChatColor.GOLD + "player is under water: " + extrasPlayer.isUnderWater(TargetP));
					player.sendMessage(ChatColor.GOLD + "player last killed " + TargetP.getDisplayName() + ": " + TargetP.getKiller());
				}else if(args.length >= 2) {
					player.sendMessage(ChatColor.GREEN + "Please only do one player at a time!");
				}
			}else if(!(player.hasPermission("BC.ginfo.2") || player.hasPermission("BC.ginfo.*"))) {
				if(args.length == 0) {
					player.sendMessage(ChatColor.GOLD + "Hour's lived: " + extrasPlayer.getHoursLived(player));
					player.sendMessage(ChatColor.GOLD + "player is under water: " + extrasPlayer.isUnderWater(player));
					player.sendMessage(ChatColor.GOLD + "player last killed " + player.getDisplayName() + ": " + player.getKiller());
				}else if(args.length >= 1) {
					player.sendMessage(ChatColor.RED + "You only have permission to look at your own info");
				}
			}
		}			
		return false;
	}
}