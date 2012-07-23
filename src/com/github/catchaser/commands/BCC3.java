package com.github.catchaser.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.BaseCommands;

public class BCC3 extends JavaPlugin implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private BaseCommands plugin;
	
	public BCC3(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("")) {
			
		}
		
		return false;
	}

}
