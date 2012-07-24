package com.github.catchaser.home;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.BaseCommands;



public class HomeStore extends JavaPlugin {
	

	private BaseCommands plugin;
	
	public HomeStore(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	private File storageFile;
	public String names;
	public String locc;
	
	
	public HomeStore(File file) {
		this.storageFile = file;
		
		if(!(this.storageFile.exists())) {
			try {
				this.storageFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

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

	public void save(String names, String locc) { //saves the location of the player to the home file
		try
		{
			//Checking to see if there is already a home file for the player
			boolean success11 = (new File("plugins/BaseCommands/homes/" + names + ".txt")).createNewFile();
			if(success11)
			{
				//writing location of player to the home file if it is the first time
				Writer output = new FileWriter("plugins/BaseCommands/homes/" + names + ".txt", true);
				output.write(locc);
				output.close();
				Firsthome();
			}else {
				//writing location of player to the home file if it is not the first time after deleting the previous home file

                File homefile = new File("plugins/BaseCommands/homes/" + names + ".txt");
                homefile.delete();
                File newhomefile = new File("plugins/BaseCommands/homes/" + names + ".txt");
                newhomefile.createNewFile();
				Writer output = new FileWriter("plugins/BaseCommands/homes/" + names + ".txt", true);
				output.flush();
				output.write(locc);
				output.close();
				notfh();
			}
		}
		catch(IOException x)
		{
			System.out.println(x.toString());
		}
	}
	
	private void notfh() {
		plugin.getServer().getPlayer(names).sendMessage(ChatColor.GREEN + "Home created!");
	}

	private void Firsthome() {
		plugin.getServer().getPlayer(names).sendMessage(ChatColor.GREEN +" First Home Created!");
	}
	
	private void tphome() {
		plugin.getServer().getPlayer(names).sendMessage(ChatColor.GOLD + "You teleported to home!");
	}
}