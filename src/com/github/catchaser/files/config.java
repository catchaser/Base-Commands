package com.github.catchaser.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.plugin.PluginDescriptionFile;

import com.github.catchaser.BaseCommands;

public class config {
	
	private BaseCommands plugin;
	
	public config(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public void configl() {
		PluginDescriptionFile pdfFile = plugin.getDescription();
		String ver = "0.1.12fk";
		String str = plugin.getConfig().getString("version#");
		 if(new File("plugins/BaseCommands/config.yml").exists()) { //checks if config.yml already exsits
				plugin.logger.info("[BaseCommands] Config Loaded"); //loads the config.yml
			}else{
			    plugin.logger.info("[BaseCommands] Config Created");
			}
		 if(str != null) {
			 if(plugin.getConfig().getString("version#").equals(ver)) {
				 plugin.logger.info(plugin.PREFIX + " Config is up to date!");
			 }else if(!(pdfFile.getVersion().equals(ver))) {
				 plugin.logger.severe(plugin.PREFIX + " Config Outdated/Corrupted!!");
				 plugin.logger.warning(plugin.PREFIX + " THIS WILL RESET THE ENTIRE CONFIG!");
				 plugin.logger.warning(plugin.PREFIX + " ANY MODIFICATIONS TO THE CONFIG WILL BE ERASED SO YOU WILL HAVE TO SET THEM AGAIN!");
				 plugin.logger.info(plugin.PREFIX + " Replacing old/corrupted config!");
				 File configFile = new File("plugins/BaseCommands/config.yml");
				 configFile.delete();
				 checkConfig();
			 }
		 }else if(str == null){
			 plugin.logger.severe(plugin.PREFIX + " Config Outdated/Corrupted!!");
			 plugin.logger.warning(plugin.PREFIX + " THIS WILL RESET THE ENTIRE CONFIG!");
			 plugin.logger.warning(plugin.PREFIX + " ANY MODIFICATIONS TO THE CONFIG WILL BE ERASED SO YOU WILL HAVE TO SET THEM AGAIN!");
			 plugin.logger.info(plugin.PREFIX + " Replacing old/corrupted config!");
			 File configFile = new File("plugins/BaseCommands/config.yml");
			 configFile.delete();
			 checkConfig();
		 }
	        if(plugin.getConfig().getString("passwd").equals("true")) {
	        	plugin.logger.info(plugin.PREFIX + " Password enabled!");
	        	plugin.passwod = true;
	        }else if(plugin.getConfig().getString("passwd").equals("false")) {
	        	plugin.logger.info(plugin.PREFIX + " Password disabled!");
	        }
	        if(plugin.getConfig().getString("signwarp").equals("true")) {
	        	plugin.logger.info(plugin.PREFIX + " SignWarp enabled!");
	        	plugin.signw = true;
	        }else if(plugin.getConfig().getString("signwarp").equals("false")) {
	        	plugin.logger.info(plugin.PREFIX + " SignWarp disabled");
	        }
	}
	
	public void checkConfig() {
		  if(!checkFile("config.yml", plugin.getDataFolder())) {
		    plugin.getLogger().warning("Error creating config!");
		  }
		}
		public boolean checkFile(String filename, File directory) {
		  if(!(new File(directory, filename)).exists()) {
		    if(!extractFile(filename, directory)) {
		      return false;
		    }
		  }
		  return true;
		}
		public boolean extractFile(String filename, File destination) {
		  File outputFile = new File(destination, filename);
		  DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		  Date date = new Date();
		  String theDate = dateFormat.format(date);
		  File backup = new File(destination, theDate + filename);
		  try {
		    if(outputFile.exists()) {
		      outputFile.renameTo(backup);
		    }
		    destination.mkdir();
		    if(getClass().getResourceAsStream("/" + filename) == null) {
		      if(backup.exists()) {
		        backup.renameTo(outputFile);
		      }
		      System.out.println("File not found in jar: " + filename);
		      return false;
		    }
		    outputFile.createNewFile();
		    InputStream is = getClass().getResourceAsStream("/" + filename);
		    FileOutputStream fos = new FileOutputStream(outputFile);
		    byte[] buffer = new byte[1024];
		    int bytesRead;
		    while((bytesRead = is.read(buffer)) > 0) {
		      fos.write(buffer, 0, bytesRead);
		    }
		    fos.flush();
		    fos.close();
		    is.close();
		  }
		  catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Error extracting file: " + filename);
		  }
		  return true;
		}

}
