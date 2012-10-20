package src.com.github.catchaser.spawn;

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

import src.com.github.catchaser.BaseCommands;

public class BCListener implements CommandExecutor {
	private BaseCommands plugin;
	
	public BCListener(BaseCommands plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = null;
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		if(commandLabel.equalsIgnoreCase("spawn")){
			if(player == null) {
				sender.sendMessage(plugin.PREFIX + " This is a player only command!");
			}else if(player != null) {
				if(player.hasPermission("BC.home.spawn") || player.hasPermission("BC.home.*") || player.hasPermission("BC.*")) {
					try
					{
						BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/spawn"));
						String ln = br.readLine();
						String coords[] = ln.split("\\,");
						br.close();
						if(Integer.parseInt(coords[0]) != 0)
						{
							Location loc = new Location(plugin.getServer().getWorld(coords[5]), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Integer.parseInt(coords[4]));
							player.teleport(loc);
							player.sendMessage(ChatColor.GOLD + "You have teleported to the spawn!");
							plugin.logger.info("[BaseCommands] " + player + " has been teleported to the spawn!");
						}else{
							Location loc = new Location(plugin.getServer().getWorld(coords[5]), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Integer.parseInt(coords[4]));
							player.teleport(loc);
							player.sendMessage(ChatColor.GOLD + "You have teleported to the spawn!");
							plugin.logger.info("[BaseCommands] " + player + " has been teleported to the spawn!");
						}
					}
					catch(IOException ex)
					{
						System.out.println(ex.toString());
					}
					return true;
					}else if(!(player.hasPermission("BC.home.spawn") || player.hasPermission("BC.home.*") || player.hasPermission("BC.*"))){
			 			String message = plugin.getConfig().getString("PERM");
				    	message = ChatColor.translateAlternateColorCodes('&', message);
				    	message = ChatColor.translateAlternateColorCodes('$', message);
				    	message = ChatColor.translateAlternateColorCodes('%', message);
				    	player.sendMessage(message);
					}
			}
		}
		if(commandLabel.equalsIgnoreCase("setspawn")){
			if(player == null) {
				sender.sendMessage(plugin.PREFIX + " This is a player only command!");
			}else if(player != null) {
				if(player.hasPermission("BC.home.setspawn") || player.hasPermission("BC.home.*") || player.hasPermission("BC.*")){
					try
					{
		                Writer output = new FileWriter("plugins/BaseCommands/spawn", false);
		                output.flush();
		                output.write((new StringBuilder(String.valueOf((int)player.getLocation().getX()))).append(",").append((int)player.getLocation().getY()).append(",").append((int)player.getLocation().getZ()).append(",").append((int)player.getLocation().getYaw()).append(",").append((int)player.getLocation().getPitch()).append(",").append(player.getWorld().getName()).toString());
		                output.close();
		                plugin.logger.info("[BaseCommands] " + player + " has set a new spawn!");
		                player.sendMessage(ChatColor.GOLD + "You have set a new spawn!");
		                
					}
					catch(IOException ex)
					{
						System.out.println(ex.toString());
					}
					
				}else if(!(player.hasPermission("BC.home.setspawn") || player.hasPermission("BC.home.*") || player.hasPermission("BC.*"))){
		 			String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	player.sendMessage(message);
				}
					return true;
			}
	    }
		return false;
	}

}