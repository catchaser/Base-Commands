package src.com.github.catchaser.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import src.com.github.catchaser.BaseCommands;

public class BCC2 extends JavaPlugin implements CommandExecutor{
	public static final String PREFIX = ChatColor.GREEN + "[BaseCommands]" + ChatColor.WHITE;
	
	private BaseCommands plugin;
	
	public BCC2(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player) sender;
		}
		if(commandLabel.equalsIgnoreCase("time")) {
			if(p == null) {
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
	                plugin.getServer().broadcastMessage(ChatColor.GRAY + "Time set to " + time.toLowerCase());
	                sender.sendMessage(PREFIX + " Time set to:" + time.toLowerCase());
			}else if(p != null) {
				if(p.hasPermission("BC.env.time")|| p.hasPermission("BC.env.*") || p.hasPermission("BC.*")) {
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
				}else if(!(p.hasPermission("BC.env.time") || p.hasPermission("BC.env.*") || p.hasPermission("BC.*"))) {
					String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
					return true;
				}
			}
		}
		if(commandLabel.equalsIgnoreCase("weather")) {
			if(p == null) {
				// What are we setting to?
                String weatherType = "";
                if(args.length > 0 && args[0] != null)
                    weatherType = args[0];

                // Get worlds list
                List<World> worlds = plugin.getServer().getWorlds();

                // Get arg if exists
                for(World world : worlds)
                {
                    if(weatherType.compareToIgnoreCase("sun") == 0) {
                        world.setStorm(false);
                        world.setThundering(false);
                    }else if(weatherType.compareToIgnoreCase("rain") == 0) {
                        world.setStorm(true);
                    }else if	(weatherType.compareToIgnoreCase("storm") == 0) {
                    	world.setThundering(true);
                        world.setStorm(true);
                    }else 
                        return false; // Failed
                }

                // Say we changed the weather
                plugin.getServer().broadcastMessage(ChatColor.GRAY + "Weather set to " + weatherType.toLowerCase());
                sender.sendMessage(PREFIX + " Weather set to:" + weatherType.toLowerCase());
			}else if(p != null) {
				if(p.hasPermission("BC.env.weather") || p.hasPermission("BC.env.*") || p.hasPermission("BC.*")) {
					// What are we setting to?
	                String weatherType = "";
	                if(args.length > 0 && args[0] != null)
	                    weatherType = args[0];

	                // Get worlds list
	                List<World> worlds = plugin.getServer().getWorlds();

	                // Get arg if exists
	                for(World world : worlds)
	                {
	                    if(weatherType.compareToIgnoreCase("sun") == 0) {
	                        world.setStorm(false);
	                        world.setThundering(false);
	                    }else if(weatherType.compareToIgnoreCase("rain") == 0) {
	                        world.setStorm(true);
	                    }else if	(weatherType.compareToIgnoreCase("storm") == 0) {
	                    	world.setThundering(true);
	                        world.setStorm(true);
	                    }else 
	                        return false; // Failed
	                }

	                // Say we changed the weather
	                p.getServer().broadcastMessage(ChatColor.GRAY + "Weather set to " + weatherType.toLowerCase());
				}else if(!(p.hasPermission("BC.env.weather") || p.hasPermission("BC.env.*") || p.hasPermission("BC.*"))) {
					String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
				}
			}
		}
		if(commandLabel.equalsIgnoreCase("kick")) {
			if(p == null) {
				sender.sendMessage(PREFIX + " This is a Player only Command!");
			}else if(p != null) {
				if(p.hasPermission("BC.admin.kick") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*")) {
		            if(args.length < 1 || args.length > 2)
		                return false;
		            
		            // Kick with args
		            PlayerKick(p, args);
		            Player target = p.getServer().getPlayer(args[0]);
		            if(target != null) {
		            	p.getServer().broadcastMessage(ChatColor.RED + p.getName() + " has kicked player " + target.getName());
		            }else if(target == null){
		            	p.sendMessage(ChatColor.DARK_PURPLE + "Player is Offline!");
		            }
		            
				  }else if(!(p.hasPermission("BC.admin.kick") || p.hasPermission("BC.admin.*") || p.hasPermission("BC.*"))){
						String message = plugin.getConfig().getString("PERM");
				    	message = ChatColor.translateAlternateColorCodes('&', message);
				    	message = ChatColor.translateAlternateColorCodes('$', message);
				    	message = ChatColor.translateAlternateColorCodes('%', message);
				    	p.sendMessage(message);
				  }
			}
		}
		if(commandLabel.equalsIgnoreCase("kill")) {
			if(p == null) {
				// Do we have an arg?
                if(args.length > 1)
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
                                sender.sendMessage(PREFIX + " " + ChatColor.GOLD + targetPlayer[j].getDisplayName() + "has been killed");
                                targetPlayer[j].sendMessage(ChatColor.GRAY + "You have been killed by " + sender.getName());
                            }
                        }
                    }
                }else if(args.length  == 1) {
                	Player tp = (Player) plugin.getServer().getPlayer(args [0]);
                	if(tp == null) {
                		sender.sendMessage(PREFIX + ChatColor.BLUE + " Player is offline!");
                	}else if(tp != null) {
                		tp.sendMessage(ChatColor.RED + "You were killed by: " + sender.getName());
                		tp.setHealth(0);
                	}
                
                }else if (args.length  == 0){ // kill self
                	sender.sendMessage(PREFIX + " Usage: kill <player>");
                }
			}else if(p != null) {
				if(p.hasPermission("BC.heal.kill") || p.hasPermission("BC.heal.*") || p.hasPermission("BC.*")) {
					// Do we have an arg?
	                if(args.length > 1)
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
	                }else if(args.length  == 1) {
	                	Player tp = (Player) p.getServer().getPlayer(args [0]);
	                	if(tp == null) {
	                		p.sendMessage(ChatColor.BLUE + "Player is offline!");
	                	}else if(tp != null) {
	                		tp.sendMessage(ChatColor.RED + "You were killed by: " + p.getName());
	                		tp.setHealth(0);
	                	}
	                
	                }else if (args.length  == 0){ // kill self
	                	p.sendMessage(ChatColor.GREEN + "Killing you!");
	                    p.setHealth(0);
	                }
				}else if(!(p.hasPermission("BC.heal.kill") || p.hasPermission("BC.heal.*") || p.hasPermission("BC.*"))) {
					String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
				}
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
