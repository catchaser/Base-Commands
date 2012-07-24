package com.github.catchaser.home;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.BaseCommands;
import com.github.catchaser.home.HomeStore;

import couk.Adamki11s.Extras.Colour.ExtrasColour;

public class home extends JavaPlugin implements CommandExecutor {
	public static final Logger logger = Logger.getLogger("Minecraft");
	private BaseCommands plugin;
	public HomeStore house;
	public String loc;
	public String name;
	PluginDescriptionFile pdfFile = this.getDescription();
	
	public home(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		ExtrasColour eC = new ExtrasColour();
		if(commandLabel.equalsIgnoreCase("sethome")) {
			if(p.hasPermission("BC.home.sethome") || p.hasPermission("BC.home.*")) {
			try
			{
				//Checking to see if there is already a home file for the player
				boolean success = (new File("plugins/BaseCommands/homes/" + p.getName() + ".txt")).createNewFile();
				if(success)
				{
					//writing location of player to the home file if it is the first time
					Writer output = new FileWriter("plugins/BaseCommands/homes/" + p.getName() + ".txt", true);
	                output.write((new StringBuilder(String.valueOf((int)p.getLocation().getX()))).append(",").append((int)p.getLocation().getY()).append(",").append((int)p.getLocation().getZ()).append(",").append((int)p.getLocation().getYaw()).append(",").append((int)p.getLocation().getPitch()).append(",").append(p.getWorld().getName()).toString());
					output.close();
					p.sendMessage(ChatColor.GREEN +" First Home Created!");
				}else {
					//writing location of player to the home file if it is not the first time after deleting the previous home file

                    File homefile = new File("plugins/BaseCommands/homes/" + p.getName() + ".txt");
                    homefile.delete();
                    File newhomefile = new File("plugins/BaseCommands/homes/" + p.getName() + ".txt");
                    newhomefile.createNewFile();
					Writer output = new FileWriter("plugins/BaseCommands/homes/" + p.getName() + ".txt", true);
					output.flush();
	                output.write((new StringBuilder(String.valueOf((int)p.getLocation().getX()))).append(",").append((int)p.getLocation().getY()).append(",").append((int)p.getLocation().getZ()).append(",").append((int)p.getLocation().getYaw()).append(",").append((int)p.getLocation().getPitch()).append(",").append(p.getWorld().getName()).toString());
					output.close();
					p.sendMessage(ChatColor.GREEN +"Home Created!");
				}
			}
			catch(IOException x)
			{
				System.out.println(x.toString());
			}
		  }else if(!(p.hasPermission("BC.home.sethome") || p.hasPermission("BC.home.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM"); //locates and reads the string
				for(String per : perm)
					p.sendMessage(per);
		  }
		}
		if(commandLabel.equalsIgnoreCase("home")) {
			if(p.hasPermission("BC.home.home") || p.hasPermission("BC.home.*")) {
				if(new File("plugins/BaseCommands/homes/" + p.getName() + ".txt").exists()) {
				house.load(p.getName());
			}else if(!(new File("plugins/BaseCommands/homes/" + p.getName() + ".txt").exists())) {
				eC.sendColouredMessage(p, "No home has been set! Set a home with /sethome");
			}
		  }else if(!(p.hasPermission("BC.home.home") || p.hasPermission("BC.home.*"))) {
				List<String>  perm = plugin.getConfig().getStringList("PERM");
				for(String per : perm)
					p.sendMessage(per);
		  }
		}
		return false;
	}
	

	
}