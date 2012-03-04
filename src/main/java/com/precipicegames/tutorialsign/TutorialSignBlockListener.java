package com.precipicegames.tutorialsign;

import org.bukkit.Material;
import org.bukkit.block.Sign;
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
			if (event.getBlock().getType() == Material.SIGN || event.getBlock().getType() == Material.SIGN_POST){
				Sign sign = (Sign) event.getBlock();
				if (sign.getLine(0).equals("[TUTORIAL]")){
					if (!event.getPlayer().hasPermission("tutorialsign.place")){
						event.setCancelled(true);
					}
				}
			
			}
		}
		
	}
	
}
