package com.github.catchaser.commands.passwd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.catchaser.BaseCommands;

import couk.Adamki11s.Extras.Cryptography.ExtrasCryptography;

public class passwd implements CommandExecutor{
	
	public BaseCommands plugin;
	ExtrasCryptography extrasCrypt = new ExtrasCryptography();
	public String pass;
	
	public passwd(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(commandLabel.equalsIgnoreCase("passwd")) {
			if(plugin.passwod) {
				if(new File("plugins/BaseCommands/passwds/" + p.getName()).exists()) {
					if(args.length == 1) {
						String pa = args [0];
						String str;
						try {
							BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/passwds/" + p.getName()));
								String ln = br.readLine();
								str = ln.toString();
								br.close();
								if(pa.equals(str)) {
									plugin.freeze = false;
									plugin.mute = false;
									p.sendMessage(ChatColor.GOLD + "Access Granted!");
								}else if(!(pa.equals(str))) {
									p.sendMessage(ChatColor.RED + "ERROR WRONG PASSWORD!");
								}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else if(args.length != 1) {
						
					}
				}else if(!(new File("plugins/BaseCommands/passwds/" + p.getName()).exists())) {
					p.sendMessage(ChatColor.RED + "You have not set a password set one with /setpasswd");
				}
			}else if(!(plugin.passwod)) {
				p.sendMessage(ChatColor.GREEN + "The password feature is disabled!");
			}
		}
		if(commandLabel.equalsIgnoreCase("setpasswd")) {
			if(plugin.passwod) {
				if(args.length == 1) {
					pass = args[0];
					if(new File("plugins/BaseCommands/passwds/" + p.getName()).exists()) {
						p.sendMessage(ChatColor.BLUE + "You already have a password!");
						p.sendMessage(ChatColor.BLUE + "You can reset your password by typing /passreset");
					}else if(!(new File("plugins/BaseCommands/passwds/" + p.getName()).exists())) {
						try {
							new File("plugins/BaseCommands/passwds/" + p.getName()).createNewFile();
							FileWriter w = new FileWriter("plugins/BaseCommands/passwds/" + p.getName());
							w.write(pass);
							w.close();
							p.sendMessage(ChatColor.GREEN + "Password Set!");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else if(args.length != 1) {
					p.sendMessage(ChatColor.GREEN + "Usage: /setpasswd <password>");
				}
			}else if(!(plugin.passwod)) {
				p.sendMessage(ChatColor.GREEN + "The password feature is disabled!");
			}
		}
		if(commandLabel.equalsIgnoreCase("resetpasswd")) {
			if(plugin.passwod) {
				if(p.hasPermission("BC.admin.passwd.reset") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*")) {
					if(new File("plugins/BaseCommands/passwds/" + p.getName()).exists()) {
				        File passwdfile = new File("plugins/BaseCommands/passwds/" + p.getName());
	                    passwdfile.delete();
	                    p.sendMessage(ChatColor.DARK_AQUA + "Password Reset!");
	                    p.sendMessage(ChatColor.GOLD + "Please set a new password with /setpasswd <password>");
					}else if(!(new File("plugins/BaseCommands/passwds/" + p.getName()).exists())) {
						p.sendMessage(ChatColor.RED + "There is no password to reset!");
					}
				}else if(!(p.hasPermission("BC.admin.passwd.reset") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*"))) {
		 			String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
				}
			}else if(!(plugin.passwod)) {
				p.sendMessage(ChatColor.GREEN + "The password feature is disabled!");
			}
		}
		return false;
	}
}
