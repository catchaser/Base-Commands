package com.github.catchaser.home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.BaseCommands;



public class HomeStore extends JavaPlugin {
	

	private BaseCommands plugin;
	
	public HomeStore(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public String names;

	public void load(String names) { //loads the players home file
		
			try
			{
				//reading the players home file
				BufferedReader br = new BufferedReader(new FileReader("plugins/BaseCommands/homes/" + names + ".txt"));
				String ln = br.readLine();
				String coords[] = ln.split("\\,");
				br.close();
				if(Integer.parseInt(coords[0]) != 0)
				{
					//teleporting player to home
							Location loc = new Location(plugin.getServer().getWorld(coords[5]), Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), Integer.parseInt(coords[4]));
							plugin.getServer().getPlayer(names).teleport(loc);
							tphome();
				}
			}
			catch(IOException ex)
			{
				System.out.println(ex.toString());
			}
	}
	
	private void tphome() {
		plugin.getServer().getPlayer(names).sendMessage(ChatColor.GOLD + "You teleported to home!");
	}
}