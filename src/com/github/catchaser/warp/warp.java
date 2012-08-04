package com.github.catchaser.warp;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.BaseCommands;
 
public class warp extends JavaPlugin implements CommandExecutor {
    public static final Logger logger = Logger.getLogger("Minecraft");
    private BaseCommands plugin;
    private String path = "plugins/BaseCommands/warps/";
    private String files;
    File folder = new File(path);
    File[] listOfFiles = folder.listFiles(); 
    PluginDescriptionFile pdfFile = this.getDescription();
 
    public warp(BaseCommands plugin) {
        this.plugin = plugin;
    }
 
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = null;
        if(sender instanceof Player) {
        	p = (Player) sender;
        }
        if(commandLabel.equalsIgnoreCase("setwarp")) {
        	if(p == null) {
        		sender.sendMessage(plugin.PREFIX + " This is a player only command!");
        	}else if(p != null) {
        		if(p.hasPermission("BC.warp.set") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*")) {
            		if(args.length  == 1) {
            			try
                        {
                            //Checking to see if there is already a warp file
                            boolean success11 = (new File("plugins/BaseCommands/warps/" + args [0])).createNewFile();
                            if(success11)
                            {
                                //writing location of player to the warp file
                                Writer output = new FileWriter("plugins/BaseCommands/warps/" + args [0], true);
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
            			p.sendMessage(ChatColor.RED  + "Usage: /setwarp <warpname>");
            		}
            		plugin.getPluginLoader().disablePlugin(plugin);
            		plugin.getPluginLoader().enablePlugin(plugin);
              }else if(!(p.hasPermission("BC.warp.set") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*"))) {
      			String message = plugin.getConfig().getString("PERM");
    		    	message = ChatColor.translateAlternateColorCodes('&', message);
    		    	message = ChatColor.translateAlternateColorCodes('$', message);
    		    	message = ChatColor.translateAlternateColorCodes('%', message);
    		    	p.sendMessage(message);
              }
        	}
        }
        if(commandLabel.equalsIgnoreCase("warp")) {
            if(p == null) {
            	sender.sendMessage(plugin.PREFIX + " This is a player only command!");
            }else if(p != null) {
            	if(!(args.length == 0)) {
                	if(p.hasPermission("BC.warp.warp") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*")) {
                			try{
                				if(!(new File("plugins/BaseCommands/warps/" + args[0]).exists())) {
                					p.sendMessage(ChatColor.GREEN + "Warp does not exist!");
                				}else{
                    			//reading the warp file
                				BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/warps/" + args[0]));
                				String ln = br.readLine();
                				String coords[] = ln.split("\\,");
                				br.close();
                				if(Integer.parseInt(coords[0]) != 0)
                				{
                					//teleporting player to warp location
                					Location warp = new Location(plugin.getServer().getWorld(coords[5]), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Integer.parseInt(coords[4]));
                					p.teleport(warp);
                					p.sendMessage(ChatColor.GOLD + "You teleported to: " + args[0]);
                				}
                			}
                			}
                			catch(IOException ex)
                			{
                				System.out.println(ex.toString());
                			}
                	}else if(!(p.hasPermission("BC.warp.warp") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*"))) {
            			String message = plugin.getConfig().getString("PERM");
    			    	message = ChatColor.translateAlternateColorCodes('&', message);
    			    	message = ChatColor.translateAlternateColorCodes('$', message);
    			    	message = ChatColor.translateAlternateColorCodes('%', message);
    			    	p.sendMessage(message);
                	}
                }else if(args.length == 1) {
                	p.sendMessage(ChatColor.BLUE + "usage /warp <warpname>");
                }
            }
        }
        if(commandLabel.equalsIgnoreCase("delwarp")) {
        	if(p == null) {
        		if(args.length == 1) {
        			if(new File("plugins/BaseCommands/warps/" + args[0]).exists()) {
        				File warpFile = new File("plugins/BaseCommands/warps/" + args [0]);
            			warpFile.delete();
            			sender.sendMessage(plugin.PREFIX + ChatColor.BLUE + " warp: " + args [0] + " has beed deleted");
        			}else if(!(new File("plugins/BaseCommands/warps/" + args[0]).exists())) {
        				sender.sendMessage(plugin.PREFIX + ChatColor.RED + " Warp does not exist!");
        			}
        		}else if(!(args.length == 1)) {
			    	sender.sendMessage(plugin.PREFIX + ChatColor.YELLOW + " Only delete one warp at a time!");
        		}
        	}else if(p != null) {
        		if(p.hasPermission("BC.warp.del") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*")) {
            		if(args.length == 1) {
            			if(new File("plugins/BaseCommands/warps/" + args[0]).exists()) {
            				File warpFile = new File("plugins/BaseCommands/warps/" + args [0]);
                			warpFile.delete();
                			p.sendMessage(ChatColor.BLUE + "warp: " + args [0] + " has beed deleted");
            			}else if(!(new File("plugins/BaseCommands/warps/" + args[0]).exists())) {
            				p.sendMessage(ChatColor.GREEN + "Warp does not exist!");
            			}
            		}else if(!(args.length == 1)) {
    			    	p.sendMessage(ChatColor.YELLOW + "Only delete one warp at a time!");
            		}
            	}else if(!(p.hasPermission("BC.warp.del") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*"))) {
            		String message = plugin.getConfig().getString("PERM");
			    	message = ChatColor.translateAlternateColorCodes('&', message);
			    	message = ChatColor.translateAlternateColorCodes('$', message);
			    	message = ChatColor.translateAlternateColorCodes('%', message);
			    	p.sendMessage(message);
            	}
        	}
        }
        if(commandLabel.equalsIgnoreCase("warplist")) {
        	if(p == null) {
        		sender.sendMessage(plugin.PREFIX + ChatColor.YELLOW + "Warps:");
       		 for (int i = 0; i < listOfFiles.length; i++) 
       		  {
       		 
       		   if (listOfFiles[i].isFile()) 
       		   {
       		   files = listOfFiles[i].getName();
       		   sender.sendMessage(files);
       		      }
       		  }
        	}else if(p != null) {
        		if(p.hasPermission("BC.warp.list") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*")) {
            		p.sendMessage(ChatColor.GREEN + "Warps:");
            		 for (int i = 0; i < listOfFiles.length; i++) 
            		  {
            		 
            		   if (listOfFiles[i].isFile()) 
            		   {
            		   files = listOfFiles[i].getName();
            		   p.sendMessage(files);
            		      }
            		  }
            	}else if(!(p.hasPermission("BC.warp.list") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*"))) {
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