package com.github.catchaser.listeners;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.github.catchaser.BaseCommands;

public class BCspawnPlayerListener implements Listener {
	private BaseCommands plugin;
	
	public BCspawnPlayerListener(BaseCommands plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if(!event.getPlayer().hasPlayedBefore()){
		plugin.getServer().broadcastMessage("[BaseCommands] First join for " + event.getPlayer().getName());
			BaseCommands.logger.info((new StringBuilder()).append("[BaseCommands] First join for ").append(event.getPlayer().getName()).toString() + ".... Teleporting to spawn");
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/spawn"));
			String ln = br.readLine();
			String coords[] = ln.split("\\,");
			br.close();
			if(Integer.parseInt(coords[0]) != 0)
			{
				Location loc = new Location(plugin.getServer().getWorld(coords[5]), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Integer.parseInt(coords[4]));
				event.getPlayer().teleport(loc);
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex.toString());
		}
	}
	}
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event){
		 try
	        {
	            BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/spawn"));
	            String ln = br.readLine();
	            String coords[] = ln.split("\\,");
	            br.close();
	            if(Integer.parseInt(coords[0]) != 0) {
					Location loc = new Location(plugin.getServer().getWorld(coords[5]), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Integer.parseInt(coords[4]));
					event.getPlayer().teleport(loc);
	            }
	        }
	        catch(IOException x)
	        {
	            System.out.println(x.toString());
	        }
	}

}