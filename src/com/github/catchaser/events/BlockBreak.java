package com.github.catchaser.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.github.catchaser.BaseCommands;

public class BlockBreak implements Listener{
	
	private BaseCommands plugin;
	
	public BlockBreak(BaseCommands plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(plugin.blockbreaktf) {
			e.getPlayer().sendMessage("Enter your password!");
			e.setCancelled(true);
		}else if(!(plugin.blockbreaktf)){
			
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(plugin.blockbreaktf) {
			e.getPlayer().sendMessage("Enter your password!");
			e.setCancelled(true);
		}else if(!(plugin.blockbreaktf)){
			
		}
	}

}
