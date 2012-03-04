package com.precipicegames.tutorialsign;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TutorialSign extends JavaPlugin{

	public static TutorialSign plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public TutorialSignPlayerListener playerlistener = new TutorialSignPlayerListener(this);
	public TutorialSignBlockListener blockListener = new TutorialSignBlockListener(this);
	
	public void onEnable(){
		final PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info("Plugin" + pdffile.getName() + " version " + pdffile.getVersion() + " is now enabled.");
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, this.playerlistener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, this.playerlistener, Event.Priority.Normal, this);
		
		File confFile = new File("tutorials.yml");
		if (!confFile.exists()){
			try {
				confFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onDisable(){
		final PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info("Plugin" + pdffile.getName() + " version " + pdffile.getVersion() + " is now disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		SpoutPlayer splayer = (SpoutPlayer) sender;
		File confFile = new File("tutorials.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(confFile);
		if (cmd.getName().equalsIgnoreCase("tut")){
			if (args[0] != null){
				if (args[0].equalsIgnoreCase("stop")){

					SpoutManager.getSoundManager().stopMusic(splayer);
					splayer.sendMessage(ChatColor.DARK_GREEN + "The tutorial has stopped playing.");
					return true;
				}else if (args[0].equalsIgnoreCase("play")){
					if (args[1] != null && args[2] != null){
						if (splayer.isSpoutCraftEnabled()){
							String url = config.getString("tutorials.categories." + args[1] + "." + args[2] + ".url");
							SpoutManager.getSoundManager().playCustomMusic(plugin, splayer, url, true);
						}else{
							splayer.sendMessage(ChatColor.RED + "You need SpoutCraft to listen to tutorials!");
						}
					}
				}
			}
		}
	return false;
	}
}
