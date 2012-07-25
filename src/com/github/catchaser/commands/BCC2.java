package com.github.catchaser.commands;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.BaseCommands;

import java.util.*;

import couk.Adamki11s.Extras.Events.ExtrasEvents;

public class BCC2 extends JavaPlugin implements CommandExecutor{
	public static final String PREFIX = ChatColor.GREEN + "[BaseCommands]" + ChatColor.WHITE;
	ExtrasEvents eevent = new ExtrasEvents();
	
	private BaseCommands plugin;
	
	public BCC2(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(commandLabel.equalsIgnoreCase("time")) {
			if(p.hasPermission("BC.env.time")|| p.hasPermission("BC.env.*")) {
				// What are we setting to?
                String time = "";
                if(args.length > 0 && args[0] != null)
                    time = args[0];

                // Get worlds list
                List<World> worlds = plugin.getServer().getWorlds();

                // Get arg if exists
                for(World world : worlds)
                {
                    if(time.compareToIgnoreCase("dawn") == 0)
                        world.setTime(0);
                    else if(time.compareToIgnoreCase("day") == 0)
                        world.setTime(6000);
                    else if(time.compareToIgnoreCase("dusk") == 0)
                        world.setTime(12000);
                    else if(time.compareToIgnoreCase("night") == 0)
                        world.setTime(37700);
                    else if(time.compareToIgnoreCase("0") == 0)
                    	world.setTime(0);
                    else if(time.compareToIgnoreCase("6") == 0)
                    	world.setTime(6000);
                    else if(time.compareToIgnoreCase("12") == 0)
                    	world.setTime(12000);
                    else if(time.compareToIgnoreCase("24") == 0)
                    	world.setTime(37700);
                    else
                        return false; // Failed
                }

                // Say we changed the Time
                p.getServer().broadcastMessage(ChatColor.GRAY + "Time set to " + time.toLowerCase());
			}else if(!(p.hasPermission("BC.env.time") || p.hasPermission("BC.env.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM"); //locates and reads the string
				for(String per : perm)
					p.sendMessage(per);
				return true;
			}
		}
		if(commandLabel.equalsIgnoreCase("weather")) {
			if(p.hasPermission("BC.env.weather") || p.hasPermission("BC.env.*")) {
				// What are we setting to?
                String weatherType = "";
                if(args.length > 0 && args[0] != null)
                    weatherType = args[0];

                // Get worlds list
                List<World> worlds = plugin.getServer().getWorlds();

                // Get arg if exists
                for(World world : worlds)
                {
                    if(weatherType.compareToIgnoreCase("sun") == 0)
                        world.setStorm(false);
                    else if(weatherType.compareToIgnoreCase("rain") == 0)
                        world.setStorm(true);
                    else if	(weatherType.compareToIgnoreCase("storm") == 0)
                    	world.setThundering(true);
                    else 
                        return false; // Failed
                }

                // Say we changed the weather
                p.getServer().broadcastMessage(ChatColor.GRAY + "Weather set to " + weatherType.toLowerCase());
			}else if(!(p.hasPermission("BC.env.weather") || p.hasPermission("BC.env.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM"); //locates and reads the string
				for(String per : perm)
					p.sendMessage(per);
				return true;
			}
		}
		if(commandLabel.equalsIgnoreCase("kick")) {
			if(p.hasPermission("BC.admin.kick") || p.hasPermission("BC.admin.*")) {
            if(args.length < 1 || args.length > 2)
                return false;
            
            // Kick with args
            PlayerKick(p, args);
            Player target = p.getServer().getPlayer(args[0]);
            p.getServer().broadcastMessage(ChatColor.RED + p.getDisplayName() + " has kicked player " + target.getName());
		  }else if(!(p.hasPermission("BC.admin.kick") || p.hasPermission("BC.admin.*"))){
				List<String>  perm = plugin.getConfig().getStringList("PERM"); //locates and reads the string
				for(String per : perm)
					p.sendMessage(per);
				return true;
		  }
		}
		if(commandLabel.equalsIgnoreCase("kill")) {
			if(p.hasPermission("BC.heal.kill") || p.hasPermission("BC.heal.*")) {
				// Do we have an arg?
                if(args.length > 0)
                {
                    // For each arg
                    for(int i = 0; i < args.length; i++)
                    {
                        // Get player and kill if target
                        Player[] targetPlayer = plugin.getServer().getOnlinePlayers();
                        for(int j = 0; j < targetPlayer.length; j++)
                        {
                            if(targetPlayer[j].getName().compareTo(args[i]) == 0)
                            {
                                targetPlayer[j].setHealth(0);
                                p.sendMessage(ChatColor.GOLD + targetPlayer[j].getDisplayName() + "has been killed");
                                targetPlayer[j].sendMessage(ChatColor.GRAY + "You have been killed by " + p.getName());
                            }
                        }
                    }
                }
                // Else, kill self
                else{
                	p.sendMessage(ChatColor.GREEN + "Killing you!");
                    p.setHealth(0);
                }
			}else if(!(p.hasPermission("BC.heal.kill") || p.hasPermission("BC.heal.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM"); //locates and reads the string
				for(String per : perm)
					p.sendMessage(per);
				return true;
			}
		}
		return false;
	}
	
    private void PlayerKick(Player player, String[] args)
    {
        // Get the target player
        Player target = plugin.getServer().getPlayer(args[0]);
        if(target == null && player != null)
        {
            player.sendMessage(ChatColor.GRAY + "Player \"" + args[0] + "\" is not online");
            return;
        }
        
        // Do we have a declared time?
        int KickTime = -1;
        if(args.length == 2)
        {
            try
            {
                KickTime = Integer.parseInt(args[1]);
                if(KickTime < 0)
                {
                    if(player != null)
                        player.sendMessage(ChatColor.GRAY + "Unable to kick; you cannot assign negative minutes");
                    return;
                }
                else if(KickTime > 24 * 60)
                {
                    if(player != null)
                        player.sendMessage(ChatColor.GRAY + "Unable to kick; you cannot assign greater than 24 hours");
                    return;
                }
            }
            catch(Exception e)
            {
                if(player != null)
                    player.sendMessage(ChatColor.GRAY + "Unable to kick; unable parse time argument");
                return;
            }
        }
        // If player found, kick
        if(target != null)
        {
            // Get kicker's name
            String kickerName = player == null ? "Server Console" : player.getName();
                //Kicking the player
                target.kickPlayer("Kicked from the server by \"" + kickerName + "\"");
                
        }
        // Else if player not found, send message iff its a player
        else if(player != null)
        {
            player.sendMessage(ChatColor.GRAY + "Unable to kick player: player not found");
        }
        
        // All done with kick
    }
}
