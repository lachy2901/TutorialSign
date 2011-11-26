package com.precipicegames.TutorialSign;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TutorialSignPlayerListener extends PlayerListener{

	public FileConfiguration config;
	
	public static TutorialSign plugin;
	
	public TutorialSignPlayerListener(TutorialSign instance){
		plugin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event){
		Block block = event.getClickedBlock();
		File confFile = new File("tutorials.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(confFile);
		Player lachy2901 = plugin.getServer().getPlayer("lachy2901");
		if (block.getType() == Material.SIGN || block.getTypeId() == 68){
			lachy2901.sendMessage("It's a sign!");
			Sign sign = (Sign) block.getState();
			if (sign.getLine(0).equalsIgnoreCase("[TUTORIAL]")){
				lachy2901.sendMessage("It's a tutorial Sign!");
				SpoutPlayer splayer = (SpoutPlayer) event.getPlayer();
				if (splayer.isSpoutCraftEnabled()){
					String url = config.getString("tutorials.categories." + sign.getLine(1) + "." + sign.getLine(2) + ".url");
					SpoutManager.getSoundManager().playCustomMusic(plugin, splayer, url, true);
					lachy2901.sendMessage("it played!");
				}else{
					splayer.sendMessage(ChatColor.RED + "You need SpoutCraft to listen to tutorials!");
				}
			}
		}
	}
	
}
