package com.precipicegames.tutorialsign;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
}
