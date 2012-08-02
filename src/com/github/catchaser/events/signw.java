package com.github.catchaser.events;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.catchaser.BaseCommands;
import com.github.catchaser.listeners.signwmethods;

public class signw implements Listener{

	private BaseCommands plugin;
	private signwmethods swm = new signwmethods(this);
	public String pname;
	
	public signw(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSignCreate(SignChangeEvent event) {
		Player p = (Player) event.getPlayer();
		
		if(event.getLine(0).equalsIgnoreCase("[BC]")) {
			if(!(p.hasPermission("BC.warp.sign.create") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*"))) {
				event.setLine(0, ChatColor.DARK_RED + "!ERROR!");
				event.setLine(1, "you don't have");
				event.setLine(2, "permission");
				event.setLine(3, ChatColor.DARK_RED + "!ERROR!");

				return;
			}else if(p.hasPermission("BC.warp.sign.create") || p.hasPermission("BC.warp.*") || p.hasPermission("BC.*")) {
				if(event.getLine(1).equals("WARP")) {
					if(!(event.getLine(2).equals(""))) { 
						if(!(event.getLine(3).equals("Sample"))) {
							
							if(!swm.checksw(swm.warpname)){
								event.setLine(0, ChatColor.DARK_RED + "!ERROR!");
								event.setLine(1, "warp doesn't");
								event.setLine(2, "exist.");
								event.setLine(3, ChatColor.DARK_RED + "!ERROR!");
						}else{
							event.setLine(0, ChatColor.WHITE + "BaseCommands");
							event.setLine(1, ChatColor.DARK_AQUA + "WARP");
							event.setLine(2, swm.warpname);
						}
					}else if(event.getLine(3).equals("Sample")) {
						swm.warpname = event.getLine(2);
						event.setLine(0, ChatColor.WHITE + "BaseCommands");
						event.setLine(1, ChatColor.DARK_AQUA + "WARP");
						event.setLine(2, swm.warpname);
						event.setLine(3, ChatColor.GOLD + "Sample");
					}
				}else if(event.getLine(2).equals("")) {
					event.isCancelled();
					event.getBlock().breakNaturally();
					p.sendMessage(ChatColor.RED  + "NO WARP SELECTED!");
				}
			}else if(!(event.getLine(1).equals("ITEMBANK"))) {
				event.getBlock().breakNaturally();
				p.sendMessage(ChatColor.DARK_RED + "Invalid Sign");
			}
		}
	}
}
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = (Player) e.getPlayer();
		 pname = p.getName();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
	            Sign sign = (Sign) e.getClickedBlock().getState();
	            if(sign.getLine(0).equals(ChatColor.WHITE + "BaseCommands")) {
	            	if(sign.getLine(1).equals(ChatColor.DARK_AQUA + "WARP")) {
	            		if(plugin.signw) {
	            			if(sign.getLine(3).equals("") || !(sign.getLine(3).equals(ChatColor.GOLD + "Sample"))) {
	            				try{
	            	    			//reading the warp file
	            					BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/signwarps/" + sign.getLine(2)));
	            					String ln = br.readLine();
	            					String coords[] = ln.split("\\,");
	            					br.close();
	            					if(Integer.parseInt(coords[0]) != 0)
	            					{
	            						//teleporting player to warp location
	            					 Location warp = new Location(plugin.getServer().getWorld(coords[5]), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Integer.parseInt(coords[4]));
	            					e.getPlayer().teleport(warp);
	            					}
	            				}
	            				catch(IOException ex)
	            				{
	            					sign.setLine(0, ChatColor.DARK_RED + "!ERROR!");
	            					sign.setLine(1, ChatColor.DARK_RED + "");
	            					sign.setLine(2, ChatColor.DARK_RED + "");
	            					sign.setLine(3, ChatColor.DARK_RED + "");
	            					e.getPlayer().sendMessage(ChatColor.RED + "Warp no longer exsists!");
	            				}
	            			}else if(sign.getLine(3).equals(ChatColor.GOLD + "Sample")) {
	            				p.sendMessage(ChatColor.DARK_PURPLE + "This is a sample Warping sign! Get rid of the Sample on the Bottem line for it to be a normal Warping Sign");
	            			}

		            	}else if(!(plugin.signw)) {
		            		p.sendMessage(ChatColor.GREEN + "The SignWarp feature is Disabled on this Server!");
		            	}
	            	}
	            }
	            }
	        }
	    }
	}
