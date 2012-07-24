package com.github.catchaser.warp;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
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

import couk.Adamki11s.Extras.Colour.ExtrasColour;
 
public class warp extends JavaPlugin implements CommandExecutor {
    public static final Logger logger = Logger.getLogger("Minecraft");
    private BaseCommands plugin;
    PluginDescriptionFile pdfFile = this.getDescription();
 
    public warp(BaseCommands plugin) {
        this.plugin = plugin;
    }
 
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        ExtrasColour eC = new ExtrasColour();
        if(commandLabel.equalsIgnoreCase("setwarp")) {
        	if(p.hasPermission("BC.warp.set") || p.hasPermission("BC.warp.*")) {
        		if(args.length  == 1) {
        			try
                    {
                        //Checking to see if there is already a warp file
                        boolean success11 = (new File("plugins/BaseCommands/warps/" + args [0] + ".txt")).createNewFile();
                        if(success11)
                        {
                            //writing location of player to the warp file
                            Writer output = new FileWriter("plugins/BaseCommands/warps/" + args [0] + ".txt", true);
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
          }else if(!(p.hasPermission("BC.warp.set") || p.hasPermission("BC.warp.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM");
				for(String per : perm)
					p.sendMessage(per);
          }
        }
        if(commandLabel.equalsIgnoreCase("warp")) {
            if(!(args.length == 0)) {
            	if(p.hasPermission("BC.warp.warp") || p.hasPermission("BC.warp.*")) {
            			try{
            				if(!(new File("plugins/BaseCommands/warps/" + args[0] + ".txt").exists())) {
            					p.sendMessage(ChatColor.GREEN + "Warp does not exist!");
            				}else{
                			//reading the warp file
            				BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/warps/" + args[0] + ".txt"));
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
            	}else if(!(p.hasPermission("BC.warp.warp") || p.hasPermission("BC.warp.*"))) {
    				List<String>  perm = plugin.getConfig().getStringList("PERM");
    				for(String per : perm)
    					p.sendMessage(per);
            	}
            }else if(args.length == 1) {
            	p.sendMessage(ChatColor.BLUE + "usage /warp <warpname>");
            }
        }
        if(commandLabel.equalsIgnoreCase("delwarp")) {
        	if(args.length == 1) {
        		if(p.hasPermission("BC.warp.del") || p.hasPermission("BC.warp.*")) {
        			if(new File("plugins/BaseCommands/warps/" + args[0] + ".txt").exists()) {
        				File warpFile = new File("plugins/BaseCommands/warps/" + args [0] + ".txt");
            			warpFile.delete();
            			p.sendMessage(ChatColor.BLUE + "warp: " + args [0] + " has beed deleted");
        			}else if(!(new File("plugins/BaseCommands/warps/" + args[0] + ".txt").exists())) {
        				eC.sendMultiColouredMessage(p, "Warp does not exist!");
        			}
        		}else if(p.hasPermission("BC.warp.del") == false || p.hasPermission("BC.warp.*") == false) {
    				List<String>  perm = plugin.getConfig().getStringList("PERM");
    				for(String per : perm)
    					p.sendMessage(per);
        		}
        	}else if(!(args.length == 1)) {
        		p.sendMessage(ChatColor.YELLOW + "Only delete one warp at a time!");
        	}
        }
        return false;
    }
}