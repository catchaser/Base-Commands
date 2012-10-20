package src.com.github.catchaser.events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import src.com.github.catchaser.BaseCommands;

public class joining implements Listener{
	

	private BaseCommands home;
	private String MOTD;
	
	public joining(BaseCommands home) {
		this.home = home;
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void playerJoin(PlayerJoinEvent event) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("plugins/BaseCommands/MOTD.txt"));
			MOTD = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String message = MOTD;
    	message = ChatColor.translateAlternateColorCodes('&', message);
    	message = ChatColor.translateAlternateColorCodes('$', message);
    	message = ChatColor.translateAlternateColorCodes('%', message);
    	event.getPlayer().sendMessage(message);
    	if(home.passwod) {
    		home.blockbreaktf = true;
    		home.freeze = true;
    		home.mute = true;
    		event.getPlayer().sendMessage(ChatColor.GOLD + "This server has password enabled!");
    		if(new File("plugins/BaseCommands/passwds/" + event.getPlayer().getName()).exists()) {
    			event.getPlayer().sendMessage(ChatColor.GOLD + "Please enter your password with /passwd <password>");
    		}else if(!(new File("plugins/BaseCommands/passwds/" + event.getPlayer().getName()).exists())) {
    			event.getPlayer().sendMessage(ChatColor.GOLD + "Please set your password: /setpasswd <password>");
    		}
    	}
    }
}
