package com.github.catchaser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.logging.Logger;

import jline.internal.Log.Level;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.banning.BanExecutor;
import com.github.catchaser.banning.ListStore;
import com.github.catchaser.commands.BCC1;
import com.github.catchaser.commands.BCC2;
import com.github.catchaser.events.BanLogging;
import com.github.catchaser.events.Loggingin_noban;
import com.github.catchaser.home.home;
import com.github.catchaser.misc.misc;
import com.github.catchaser.spawn.BCListener;
import com.github.catchaser.warp.warp;

import couk.Adamki11s.Extras.Extras.Extras;

@SuppressWarnings("unused")
public class BaseCommands extends JavaPlugin implements Listener{
	public static Logger logger = Logger.getLogger("Minecraft");
	public static BaseCommands plugin;
	private BCC1 myExecutor;
	private ginfo myExecutor2;
	public static Permission permission = null;
	private BanExecutor MyExecutorb;
	private BCListener myExecutor3;
	private BCC2 myExecutor4;
	private home myExecutorh;
	private warp myExecutorw;
	private misc mis;
	public static final String PREFIX ="[BaseCommands]";
	public ListStore bannedPlayers;

	@SuppressWarnings("static-access")
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version: " + pdfFile.getVersion() + " has been enabled!");
        Extras ex = new Extras("BaseCommands"); 
        if(new File("plugins/BaseCommands/config.yml").exists()) {
			logger.info("[BaseCommands] Config Loaded");
		}else{
			 this.getConfig().options().copyDefaults(true);
		    this.saveConfig();
		    logger.info("[BaseCommands] Config Created");
		}
        try {
        	if(new File("BC-Banned-Players.txt").exists()) {
            	this.bannedPlayers = new ListStore(new File("BC-banned-players.txt"));
                this.bannedPlayers.load(); 
                logger.info(PREFIX + " Loaded the BC-banned-players file");
        	}else{
			new File("BC-banned-players.txt").createNewFile();
        	logger.info(PREFIX + " Loaded BC-banned-players file");
        	this.bannedPlayers = new ListStore(new File("BC-banned-players.txt"));
            this.bannedPlayers.load(); 
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        LoadCommands();
        
        this.getServer().getPluginManager().registerEvents(new Loggingin_noban(this), this);
		this.getServer().getPluginManager().registerEvents(new BanLogging(this), this);
		
    	HDIR();
		
    	WDIR();
    	
    	PDIR();
    	
    	Perm();
	}
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.bannedPlayers.save();
		logger.info("[BaseCommands] Saving Banned players");
		BaseCommands.logger.info(pdfFile.getName() + " has been Disabled!");
	}

	public boolean setupPermissions(){
		PluginDescriptionFile pdfFile = this.getDescription();
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if(permissionProvider != null){
			permission = permissionProvider.getProvider();
			logger.info(pdfFile.getName() + "Found and hooked into " + permissionProvider.getProvider().getName() + " with Vault!");
		}
		return (permission != null);
	}	
	
	public void HDIR() {
		boolean success = (new File("plugins/BaseCommands/homes/")).mkdir();
		if(success){
			logger.info("[BaseCommands] Successfully created homes directory!");
		}else{
			logger.info("[BaseCommands] Successfully loaded homes directory!");
		}
	}
	
	public void WDIR() {
		boolean success2 = (new File("plugins/BaseCommands/warps/")).mkdir();
		if(success2) {
		logger.info("[BaseCommands] Successfully created warps directory!");	
		}else{
			logger.info("[BaseCommands] Successfully loaded warps directory!");
		}
	}
	
	public void PDIR() {
		PluginDescriptionFile pdfFile = this.getDescription();
		boolean success1 = (new File("plugins/BaseCommands/")).mkdir();
		if(success1){
			logger.info("[BaseCommands] Successfully created BaseCommands directory!");
		}else
			logger.info("[BaseCommands] Successfully Loaded Plugin directory!");
		try
		{
			boolean success11 = (new File("plugins/BaseCommands/spawn.txt")).createNewFile();
			if(success11)
			{
				logger.info(pdfFile.getName() + " Successfully created spawn file!");
				Writer output = new FileWriter("plugins/BaseCommands/spawn.txt", true);
				output.write("0,0,0,0,0,0");
				output.close();
				logger.info("[BaseCommands] Set spawn.txt to default spawn!");
				logger.severe(PREFIX +"If this is the first time using this plugin please reload the server for the other directories to be Loaded");
				logger.severe(PREFIX + "if this is not the first time then you can just ignore this message");
			}else{
				logger.info("[BaseCommands] Successfully loaded spawn File!");
			}
		}
		catch(IOException x)
		{
			System.out.println(x.toString());
		}	
	}	
	
	public void LoadCommands() {
    	getCommand("heal").setExecutor(myExecutor);
    	getCommand("tp").setExecutor(myExecutor);  	
    	getCommand("rules").setExecutor(myExecutor);
    	getCommand("tphere").setExecutor(myExecutor);   	
    	getCommand("whoiso").setExecutor(myExecutor);
    	getCommand("ginfo").setExecutor(myExecutor2);
    	getCommand("ginfo2").setExecutor(myExecutor2);
    	getCommand("fly").setExecutor(myExecutor);  	
    	getCommand("dfly").setExecutor(myExecutor);  	
    	getCommand("spawn").setExecutor(myExecutor3);
		getCommand("setspawn").setExecutor(myExecutor3);
		getCommand("home").setExecutor(myExecutorh);
		getCommand("sethome").setExecutor(myExecutorh);
		getCommand("warp").setExecutor(myExecutorw);
		getCommand("setwarp").setExecutor(myExecutorw);
		getCommand("delwarp").setExecutor(myExecutorw);
		getCommand("time").setExecutor(myExecutor4);
		getCommand("weather").setExecutor(myExecutor4);
		getCommand("kill").setExecutor(myExecutor4);
		getCommand("kick").setExecutor(myExecutor4);
		getCommand("ban").setExecutor(MyExecutorb);
		getCommand("unban").setExecutor(MyExecutorb);
		getCommand("nickname").setExecutor(mis);
		getCommand("bcversion").setExecutor(mis);
		getCommand("feed").setExecutor(mis);
	}
	
	public void Perm() {
		
	}
}