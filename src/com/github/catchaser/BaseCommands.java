package com.github.catchaser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.catchaser.banning.BanExecutor;
import com.github.catchaser.banning.BanStore;
import com.github.catchaser.commands.BCC1;
import com.github.catchaser.commands.BCC2;
import com.github.catchaser.commands.ginfo;
import com.github.catchaser.events.BanLogging;
import com.github.catchaser.events.Loggingin_noban;
import com.github.catchaser.home.HomeStore;
import com.github.catchaser.home.home;
import com.github.catchaser.misc.misc;
import com.github.catchaser.spawn.BCListener;
import com.github.catchaser.warp.warp;

import couk.Adamki11s.Extras.Extras.Extras;

@SuppressWarnings("unused")
public class BaseCommands extends JavaPlugin implements Listener{
	public static Logger logger = Logger.getLogger("Minecraft");
	public static BaseCommands plugin;
	private BCC1 BCC1e;
	private ginfo ginfoe;
	private BanExecutor ban;
	private BCListener spawn;
	private BCC2 BCC2e;
	private home homee;
	private warp warpe;
	private misc mis;
	public static final String PREFIX ="[BaseCommands]";
	public BanStore bannedPlayers;
	public HomeStore house;
	public static Permission perm = null;
	
	@SuppressWarnings("static-access")
	@Override
	public void onEnable() { // Enables the plugin
		setupPermissions();
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(PREFIX + " " + pdfFile.getName() + " Version: " + pdfFile.getVersion() + " has been enabled!");
		Extras ex = new Extras("BaseCommands"); 
        if(new File("plugins/BaseCommands/config.yml").exists()) { //checks if config.yml already exsits
			logger.info("[BaseCommands] Config Loaded"); //loads the config.yml
		}else{
			 this.getConfig().options().copyDefaults(true);
		    this.saveConfig(); //creates config.yml if it doesnt exist
		    logger.info("[BaseCommands] Config Created");
		}
        try {
        	if(new File("BC-Banned-Players.txt").exists()) { //checks if BC-Banned-Players.txt exsits
            	this.bannedPlayers = new BanStore(new File("BC-banned-players.txt"));
                this.bannedPlayers.load(); //loads BC-Banned-Players.txt
                logger.info(PREFIX + " Loaded the BC-banned-players file");
        	}else{
			new File("BC-banned-players.txt").createNewFile(); //creates file if it doesnt exsit
        	logger.info(PREFIX + " Loaded BC-banned-players file");
        	this.bannedPlayers = new BanStore(new File("BC-banned-players.txt"));
            this.bannedPlayers.load(); 
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        LoadCommands(); 
        
        
        this.getServer().getPluginManager().registerEvents(new Loggingin_noban(this), this); // registers the MOTD on login event
		this.getServer().getPluginManager().registerEvents(new BanLogging(this), this); // registers the banned player on login event
		
    	HDIR();	
    	WDIR();
    	PDIR();    	
	}
	
	@Override
	public void onDisable() { //Disables the plugin
		PluginDescriptionFile pdfFile = this.getDescription();
		this.bannedPlayers.save();
		logger.info("[BaseCommands] Saving Banned players"); //saves the BC-Banned-Player.txt file
		BaseCommands.logger.info(pdfFile.getName() + " has been Disabled!");
	}
	
	public void HDIR() { //Home folder
		boolean success = (new File("plugins/BaseCommands/homes/")).mkdir();
		if(success){//Creates the home folder if it doesn't exist
			logger.info("[BaseCommands] Successfully created homes directory!");
		}else{//loads the home foler if it exists
			logger.info("[BaseCommands] Successfully loaded homes directory!");
		}
	}
	
	public void WDIR() {//warp folder
		boolean success2 = (new File("plugins/BaseCommands/warps/")).mkdir();
		if(success2) {//creates warp folder if it doesn't exist
		logger.info("[BaseCommands] Successfully created warps directory!");	
		}else{//loads the warp folder if it exists
			logger.info("[BaseCommands] Successfully loaded warps directory!");
		}
	}
	
	public void PDIR() {
		PluginDescriptionFile pdfFile = this.getDescription();
		boolean success1 = (new File("plugins/BaseCommands/")).mkdir();
		if(success1){ //creates the BaseCommands directory if it doesn't exist
			logger.info("[BaseCommands] Successfully created BaseCommands directory!");
		}else//loads BaseCommands directory if it exists
			logger.info("[BaseCommands] Successfully Loaded Plugin directory!");
		try //spawn file
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
	
	public void LoadCommands() { //loads all the commands for BaseCommands
		BCC1e = new BCC1(this);
    	getCommand("heal").setExecutor(BCC1e);
    	
        BCC1e = new BCC1(this);
    	getCommand("tp").setExecutor(BCC1e);
    	
    	BCC1e = new BCC1(this);
    	getCommand("rules").setExecutor(BCC1e);
    	
    	BCC1e = new BCC1(this);
    	getCommand("tphere").setExecutor(BCC1e);
    	
    	BCC1e = new BCC1(this);
    	getCommand("whoiso").setExecutor(BCC1e);
    	
    	ginfoe = new ginfo(this);
    	getCommand("ginfo").setExecutor(ginfoe);
    	
    	ginfoe = new ginfo(this);
    	getCommand("ginfo2").setExecutor(ginfoe);
    	
    	BCC1e = new BCC1(this);
    	getCommand("fly").setExecutor(BCC1e);
    	
    	BCC1e = new BCC1(this);
    	getCommand("dfly").setExecutor(BCC1e);
    	
    	spawn = new BCListener(this);
    	getCommand("spawn").setExecutor(spawn);
    	
    	spawn = new BCListener(this);
		getCommand("setspawn").setExecutor(spawn);
		
		homee = new home(this);
		getCommand("home").setExecutor(homee);
		
		homee = new home(this);
		getCommand("sethome").setExecutor(homee);
		
		warpe = new warp(this);
		getCommand("warp").setExecutor(warpe);

		warpe = new warp(this);
		getCommand("setwarp").setExecutor(warpe);
    	
		warpe = new warp(this);
		getCommand("delwarp").setExecutor(warpe);
		
		BCC2e = new BCC2(this);
		getCommand("time").setExecutor(BCC2e);
		
		BCC2e = new BCC2(this);
		getCommand("weather").setExecutor(BCC2e);
		
		BCC2e = new BCC2(this);
		getCommand("kill").setExecutor(BCC2e);
		
		BCC2e = new BCC2(this);
		getCommand("kick").setExecutor(BCC2e);
		
		ban = new BanExecutor(this);
		getCommand("ban").setExecutor(ban);
		
		ban = new BanExecutor(this);
		getCommand("unban").setExecutor(ban);
		
		mis = new misc(this);
		getCommand("nickname").setExecutor(mis);
		
		mis = new misc(this);
		getCommand("bcversion").setExecutor(mis);
		
		mis = new misc(this);
		getCommand("feed").setExecutor(mis);
	}
	
	public boolean setupPermissions(){ //loads the permissions using vualt
		PluginDescriptionFile pdfFile = this.getDescription();
		if(new File("plugins/Vault.jar").exists()) {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if(permissionProvider != null){
			perm = permissionProvider.getProvider();
			logger.info(PREFIX + " Found and hooked into " + permissionProvider.getProvider().getName() + " with Vault!");
		}
		return (perm != null);
		}else {
			logger.severe(PREFIX + " Vault not found Defaulting to OP/Bukkit Permissions!");
		}
		return false;
	}	
}