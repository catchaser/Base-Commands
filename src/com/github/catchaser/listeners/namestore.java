package com.github.catchaser.listeners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class namestore {
	

	
	private File storageFile;
	private ArrayList<String> name;
	public String nick;
	
	public namestore(File file) {
		this.storageFile = file;
		this.name = new ArrayList<String>();
		
		if(!(this.storageFile.exists())) {
			try {
				this.storageFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void load() { //loads the nicks.txt file
		try {
			DataInputStream input = new DataInputStream(new FileInputStream(this.storageFile));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			String line;
			
			while((line = reader.readLine()) != null) {
				if(this.name.contains(line) == false) {
					this.name.add(line);
				}
			}
			
			reader.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save() { //saves the nicked players name to the file
		try {
			FileWriter stream = new FileWriter(this.storageFile);
			BufferedWriter out = new BufferedWriter(stream);
			
			for (String value : this.name) {
				out.write(value);
				out.newLine();
			}
			out.close();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean contains(String name, String nick) {
		return this.name.contains(name + " : " + nick);
		//checks to see if the player is in the file
	}
	
	public void add(String name, String nick) {
		if(!(this.contains(name + " : " + nick, nick))) {
			this.name.add(name + " : " + nick);
			//checks to see if the player is already in the file if not it adds the player
		}else if(this.contains(name + ":" + nick, nick)) {
			this.remove(name + ":" + nick);
		}
		
	}
	
	public void remove(String name) {
		this.name.remove(name + " : " + nick); // removes the player from file
	}
	
	public ArrayList<String> getValues() {
		return this.name;
		
	}
}