package com.github.catchaser.spawn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.catchaser.BaseCommands;

public class BCListener implements CommandExecutor {
	private BaseCommands plugin;
	public static final String PERM = ChatColor.RED + "You do not have that permission!";
	
	public BCListener(BaseCommands plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("spawn")){
			if(player.hasPermission("BC.home.spawn") == true || player.hasPermission("BC.home.*")) {
			try
			{
				BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/spawn.txt"));
				String ln = br.readLine();
				String coords[] = ln.split("\\,");
				br.close();
				if(Integer.parseInt(coords[0]) != 0)
				{
					Location loc = new Location(plugin.getServer().getWorld(coords[5]), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Integer.parseInt(coords[4]));
					player.teleport(loc);
					player.sendMessage(ChatColor.GOLD + "You have teleported to the spawn!");
					BaseCommands.logger.info("[BaseCommands] " + player + " has been teleported to the spawn!");
				}
			}
			catch(IOException ex)
			{
				System.out.println(ex.toString());
			}
			return true;
			}else if(player.hasPermission("BC.home.spawn") == false || player.hasPermission("BC.home.*") == false){
				player.sendMessage(PERM);
			}
			}
		if(commandLabel.equalsIgnoreCase("setspawn")){
			if(player.hasPermission("BC.home.setspawn") ==true || player.hasPermission("BC.home.*") == true){

			try
			{
                Writer output = new FileWriter("plugins/BaseCommands/spawn.txt", false);
                output.flush();
                output.write((new StringBuilder(String.valueOf((int)player.getLocation().getX()))).append(",").append((int)player.getLocation().getY()).append(",").append((int)player.getLocation().getZ()).append(",").append((int)player.getLocation().getYaw()).append(",").append((int)player.getLocation().getPitch()).append(",").append(player.getWorld().getName()).toString());
                output.close();
                BaseCommands.logger.info("[BaseCommands] " + player + " has set a new spawn!");
                player.sendMessage(ChatColor.GOLD + "You have set a new spawn!");
                
			}
			catch(IOException ex)
			{
				System.out.println(ex.toString());
			}
			
		}else if(player.hasPermission("BC.home.setspawn") == false || player.hasPermission("BC.home.*") == false){
			player.sendMessage(PERM);
		}
			return true;
		}
		return false;
	}

}