package com.github.catchaser.misc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.catchaser.BaseCommands;

public class misc implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private BaseCommands plugin;
	
	public misc(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		return false;
	}

}
