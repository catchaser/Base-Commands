package com.github.catchaser.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.catchaser.BaseCommands;

public class help implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private BaseCommands plugin;
	
	public help(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = null;
		if(sender instanceof Player) {
			p = (Player) sender;
		}
		if(commandLabel.equalsIgnoreCase("help")) {
			if(p == null) {
				
			}else if(p != null) {
				
			}
		}
		return false;
	}

}
