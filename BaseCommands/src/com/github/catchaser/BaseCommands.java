package src.com.github.catchaser;

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

import src.com.github.catchaser.banning.BanExecutor;
import src.com.github.catchaser.commands.BCC1;
import src.com.github.catchaser.commands.BCC2;
import src.com.github.catchaser.commands.BCC3;
import src.com.github.catchaser.commands.ginfo;
import src.com.github.catchaser.commands.misc.misc;
import src.com.github.catchaser.commands.passwd.passwd;
import src.com.github.catchaser.events.BanLogging;
import src.com.github.catchaser.events.BlockBreak;
import src.com.github.catchaser.events.freezeListener;
import src.com.github.catchaser.events.joining;
import src.com.github.catchaser.events.mutedListener;
import src.com.github.catchaser.files.MOTD;
import src.com.github.catchaser.files.config;
import src.com.github.catchaser.home.home;
import src.com.github.catchaser.listeners.BanStore;
import src.com.github.catchaser.signs.signw;
import src.com.github.catchaser.spawn.BCListener;
import src.com.github.catchaser.warp.signwarp;
import src.com.github.catchaser.warp.warp;
//import com.github.catchaser.events.ChestPro;

public class BaseCommands extends JavaPlugin implements Listener{
	public Logger logger = Logger.getLogger("Minecraft");
	public static BaseCommands plugin;
	private BCC1 BCC1e;
	private BCC3 BCC3e;
	private ginfo ginfoe;
	private BanExecutor ban;
	private BCListener spawn;
	private BCC2 BCC2e;
	private signwarp swe;
	private home homee;
	private warp warpe;
	private misc mis;
	private passwd pas;
	public final String PREFIX ="[BaseCommands]";
	public BanStore bannedPlayers;
	public static Permission permission = null;
	public final freezeListener fl = new freezeListener(this);
	public final mutedListener ml = new mutedListener(this);
	public final signw sw = new signw(this);
	public final config cfg = new config(this);
	public final MOTD mtd = new MOTD(this);
	public final BlockBreak bb = new BlockBreak(this);
	//public final ChestPro CP = new ChestPro(this);
	public boolean freeze = false;
	public boolean mute = false;
	public boolean passwod = false;
	public boolean signw = false;
	public boolean signif = false;
	public boolean itemsi = false;
	public boolean blockbreaktf = false;
	public boolean Chestp = false;
	public PluginDescriptionFile pdfFile = this.getDescription();
	
	@Override
	public void onEnable() { // Enables the plugin
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(PREFIX + " " + pdfFile.getName() + " Version: " + pdfFile.getVersion() + " has been enabled!");
		setupPermissions();
        RegisterEvents();
		PDIR();
		cfg.configl();
		cfg.checkConfig();
		mtd.checkMOTD();
		banned();
        LoadCommands();
    	HDIR();	
    	WDIR();
    	passwds(); 
    	swarps();	
	}

	@Override
	public void onDisable() { //Disables the plugin
		PluginDescriptionFile pdfFile = this.getDescription();
		this.bannedPlayers.save();
		logger.info("[BaseCommands] Saving Banned players"); //saves the BC-Banned-Player.txt file
		logger.info(pdfFile.getName() + " has been Disabled!");
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
	
	public void RegisterEvents() {
		this.getServer().getPluginManager().registerEvents(new joining(this), this); // registers the MOTD on join event
		this.getServer().getPluginManager().registerEvents(new BanLogging(this), this); // registers the banned player on login event
		this.getServer().getPluginManager().registerEvents(fl, this); //registers the freeze event
		this.getServer().getPluginManager().registerEvents(ml, this);//registers the mute
		this.getServer().getPluginManager().registerEvents(sw, this); //registers the warp sign event
		this.getServer().getPluginManager().registerEvents(bb, this); //registers the Block break event
		//this.getServer().getPluginManager().registerEvents(CP, this);//registers the Chest Protection event
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
			boolean success11 = (new File("plugins/BaseCommands/spawn")).createNewFile();
			if(success11)
			{
				logger.info(pdfFile.getName() + " Successfully created spawn file!");
				Writer output = new FileWriter("plugins/BaseCommands/spawn", true);
				output.write("0,0,0,0,0,0");
				output.close();
				logger.info("[BaseCommands] Set spawn to default spawn!");
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
		BCC3e = new BCC3(this);
		BCC1e = new BCC1(this);
		ginfoe = new ginfo(this);
		spawn = new BCListener(this);
		homee = new home(this);
		warpe = new warp(this);
		BCC2e = new BCC2(this);
		mis = new misc(this);
		ban = new BanExecutor(this);
		pas = new passwd(this);
		swe = new signwarp(this);

    	getCommand("heal").setExecutor(BCC1e);
    	getCommand("tp").setExecutor(BCC1e);
    	getCommand("rules").setExecutor(BCC1e);
    	getCommand("tphere").setExecutor(BCC1e);
    	getCommand("whoiso").setExecutor(BCC1e);
    	getCommand("ginfo").setExecutor(ginfoe);
    	getCommand("fly").setExecutor(BCC1e);
    	getCommand("dfly").setExecutor(BCC1e);
    	getCommand("spawn").setExecutor(spawn);
		getCommand("setspawn").setExecutor(spawn);
		getCommand("home").setExecutor(homee);
		getCommand("sethome").setExecutor(homee);
		getCommand("warp").setExecutor(warpe);
		getCommand("setwarp").setExecutor(warpe);
		getCommand("delwarp").setExecutor(warpe);
		getCommand("warplist").setExecutor(warpe);
		getCommand("time").setExecutor(BCC2e);
		getCommand("weather").setExecutor(BCC2e);
		getCommand("kill").setExecutor(BCC2e);
		getCommand("kick").setExecutor(BCC2e);
		getCommand("ban").setExecutor(ban);
		getCommand("unban").setExecutor(ban);
		getCommand("nickname").setExecutor(mis);
		getCommand("god").setExecutor(mis);
		getCommand("unnick").setExecutor(mis);
		getCommand("bcversion").setExecutor(mis);
		getCommand("feed").setExecutor(mis);
		getCommand("msg").setExecutor(BCC3e);
		getCommand("freeze").setExecutor(BCC3e);
		getCommand("unfreeze").setExecutor(BCC3e);
		getCommand("mute").setExecutor(mis);
		getCommand("unmute").setExecutor(mis);
		getCommand("passwd").setExecutor(pas);
		getCommand("setpasswd").setExecutor(pas);
		getCommand("resetpasswd").setExecutor(pas);
		getCommand("setsignwarp").setExecutor(swe);
		getCommand("delsignwarp").setExecutor(swe);
	}
	
	public boolean setupPermissions(){ //loads the permissions using vualt
		if(new File("plugins/Vault.jar").exists()) {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if(permissionProvider != null){
			permission = permissionProvider.getProvider();
			logger.info(PREFIX + " Found and hooked into " + permissionProvider.getProvider().getName() + " with Vault!");
		}
		return (permission != null);
		}else {
			logger.severe(PREFIX + " Vault not found Defaulting to OP/Bukkit Permissions!");
		}
		return false;
	}	
	
	
	public void banned() {
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
	}
	public void passwds() {
		if(this.passwod) {
			boolean success2 = (new File("plugins/BaseCommands/passwds/")).mkdir();
			if(success2) {//creates passwords folder if it doesn't exist
			logger.info("[BaseCommands] Successfully created Password directory!");	
			}else{//loads the Password folder if it exists
				logger.info("[BaseCommands] Successfully loaded Password directory!");
			}
		}else{
			
		}
	}
	
	public void swarps() {
		if(this.signw) {
			boolean success2 = (new File("plugins/BaseCommands/signwarps/")).mkdir();
			if(success2) {//creates passwords folder if it doesn't exist
			logger.info("[BaseCommands] Successfully created SignWarps directory!");	
			}else{//loads the Password folder if it exists
				logger.info("[BaseCommands] Successfully loaded SignWarps directory!");
			}
		}else if(!(this.signw)) {
			
		}
	}		
}