package com.github.catchaser.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.github.catchaser.BaseCommands;

public class versionfiles { 
	
	private BaseCommands plugin;
	private String PluginVersion;
	private String BC = "BCVersion";
	
	public versionfiles(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	public void VersionCheck() {
		if(new File("plugins/BaseCommands/BCVersion").exists()) {
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader("plugins/BaseCommands/BCVersion"));
				PluginVersion = br.readLine();
				br.close();
				if(PluginVersion.equals(plugin.pdfFile.getVersion())) {
					plugin.logger.info(plugin.PREFIX + " BaseCommands version upto date!");
				}else if(!(PluginVersion.equals(plugin.pdfFile.getVersion()))) {
					plugin.logger.info(plugin.PREFIX + " BaseCommands version file outdated upgrading!");
					File Version = new File("plugins/BaseCommands/BCVersion");
					Version.delete();
					Version.createNewFile();
					FileWriter fw;
					fw = new FileWriter("plugins/BaseCommands/BCVersion");
					fw.write(plugin.pdfFile.getVersion());
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
		
}