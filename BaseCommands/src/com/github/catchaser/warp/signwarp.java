package src.com.github.catchaser.warp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import src.com.github.catchaser.BaseCommands;

public class signwarp implements CommandExecutor{
	
	private BaseCommands plugin;
	public static final String PREFIX = ChatColor.GREEN + "[BaseCommands]" + ChatColor.WHITE;
	
	public signwarp(BaseCommands plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = null;
		
		if (sender instanceof Player) {
			p = (Player) sender;
		}
		
		if(commandLabel.equals("setsignwarp")) {
        	if(p == null) {
        		sender.sendMessage(PREFIX + " This is a player only command!");
        	}else if(p != null) {
        		if(p.hasPermission("BC.warp.set.sign") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*")) {
            		if(args.length  == 1) {
            			try
                        {
                            //Checking to see if there is already a warp file
                            boolean success11 = (new File("plugins/BaseCommands/signwarps/" + args [0])).createNewFile();
                            if(success11)
                            {
                                //writing location of player to the warp file
                                Writer output = new FileWriter("plugins/BaseCommands/signwarps/" + args [0], true);
                                output.write((new StringBuilder(String.valueOf((int)p.getLocation().getX()))).append(",").append((int)p.getLocation().getY()).append(",").append((int)p.getLocation().getZ()).append(",").append((int)p.getLocation().getYaw()).append(",").append((int)p.getLocation().getPitch()).append(",").append(p.getWorld().getName()).toString());
                                output.close();
                                p.sendMessage(ChatColor.GREEN +"Warp Created!");
                            }else{ 
                            	p.sendMessage(ChatColor.GREEN  + "Warp already exists");
                            }
                        }
                        catch(IOException x)
                        {
                            System.out.println(x.toString());
                        }
            		}else if(!(args.length == 1)) {
            			p.sendMessage(ChatColor.RED  + "Usage: /setsignwarp <warpname>");
            		}
              }else if(!(p.hasPermission("BC.warp.set.sign") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*"))) {
      			String message = plugin.getConfig().getString("PERM");
    		    	message = ChatColor.translateAlternateColorCodes('&', message);
    		    	message = ChatColor.translateAlternateColorCodes('$', message);
    		    	message = ChatColor.translateAlternateColorCodes('%', message);
    		    	p.sendMessage(message);
              }
        	}
		}
		 if(commandLabel.equalsIgnoreCase("delsignwarp")) {
	        	if(p == null) {
	        		if(args.length == 1) {
	        			if(new File("plugins/BaseCommands/signwarps/" + args[0]).exists()) {
	        				File warpFile = new File("plugins/BaseCommands/signwarps/" + args [0]);
	            			warpFile.delete();
	            			sender.sendMessage(PREFIX + ChatColor.BLUE + " warp: " + args [0] + " has beed deleted");
	        			}else if(!(new File("plugins/BaseCommands/signwarps/" + args[0]).exists())) {
	        				sender.sendMessage(PREFIX + ChatColor.BLUE + " Warp does not exist!");
	        			}
	        		}else if(args.length != 1) {
	        			sender.sendMessage(PREFIX + ChatColor.YELLOW + " Only delete one warp at a time!");
	        		}
	        	}else if(p != null) {
	        		if(p.hasPermission("BC.warp.del.sign") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*")) {
		        		if(args.length == 1) {
		        			if(new File("plugins/BaseCommands/signwarps/" + args[0]).exists()) {
		        				File warpFile = new File("plugins/BaseCommands/signwarps/" + args [0]);
		            			warpFile.delete();
		            			p.sendMessage(ChatColor.BLUE + "warp: " + args [0] + " has beed deleted");
		        			}else if(!(new File("plugins/BaseCommands/signwarps/" + args[0]).exists())) {
		        				p.sendMessage(ChatColor.GREEN + "Warp does not exist!");
		        			}
		        		}else if(args.length != 1) {
		        			p.sendMessage(ChatColor.YELLOW + "Only delete one warp at a time!");
		        		}
		        	}else if(!(p.hasPermission("BC.warp.del.sign") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*"))) {
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

}
