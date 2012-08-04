package com.github.catchaser.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.catchaser.BaseCommands;

public class MOTD {
	
	private BaseCommands plugin;
	
	public MOTD(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public void checkMOTD() {
		  if(!checkFileMOTD("MOTD.txt", plugin.getDataFolder())) {
		    plugin.getLogger().warning("Error creating MOTD!");
		  }
		}
		public boolean checkFileMOTD(String filename, File directory) {
		  if(!(new File(directory, filename)).exists()) {
		    if(!extractFileMOTD(filename, directory)) {
		      return false;
		    }
		  }
		  return true;
		}
		public boolean extractFileMOTD(String filename, File destination) {
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
