package com.precipicegames.TutorialSign;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TutorialSignBlockListener extends BlockListener{

public FileConfiguration config;
	
	public static TutorialSign plugin;
	
	public TutorialSignBlockListener(TutorialSign instance){
		plugin = instance;
	}
	
	public void onBlockPlace(BlockPlaceEvent event){
		
		if (event.getPlayer() != null){
			if (!event.getPlayer().hasPermission("tutorialsign.place")){
				event.setCancelled(true);
			}
		}
		
	}
	
}
