package com.github.catchaser.banning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class BanStore {
	

	
	private File storageFile;
	private ArrayList<String> values;
	
	public BanStore(File file) {
		this.storageFile = file;
		this.values = new ArrayList<String>();
		
		if(!(this.storageFile.exists())) {
			try {
				this.storageFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void load() { //loads the BC-banned-players.txt file
		try {
			DataInputStream input = new DataInputStream(new FileInputStream(this.storageFile));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			String line;
			
			while((line = reader.readLine()) != null) {
				if(this.values.contains(line) == false) {
					this.values.add(line);
				}
			}
			
			reader.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save() { //saves the banned players name to the file
		try {
			FileWriter stream = new FileWriter(this.storageFile);
			BufferedWriter out = new BufferedWriter(stream);
			
			for (String value : this.values) {
				out.write(value);
				out.newLine();
			}
			out.close();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean contains(String value) {
		return this.values.contains(value);
		//checks to see if the player is in the file
	}
	
	public void add(String value) {
		if(!(this.contains(value))) {
			this.values.add(value);
			//checks to see if the player is already in the file if not it adds the player
		}
		
	}
	
	public void remove(String value) {
		this.values.remove(value); // removes the player from file
	}
	
	public ArrayList<String> getValues() {
		return this.values;
		
	}
}