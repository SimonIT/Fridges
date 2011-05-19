package com.servegame.n1p.Fridges;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Fridges extends JavaPlugin {

    Logger log = Logger.getLogger("Minecraft");
    public boolean enabled;
    
    private final FridgesPlayerListener playerListener = new FridgesPlayerListener(this);


	public void onEnable(){
		
		enabled = true;
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		
        log.info(this.getDescription().getName() + " - Version " + this.getDescription().getVersion() + " has been enabled.");

    }

    public void onDisable(){

        log.info(this.getDescription().getName() + " - Version " + this.getDescription().getVersion() + " has been disabled.");

    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

            if (sender instanceof Player) {
            	
            	Player player = (Player) sender;
            	
            		if (commandLabel.equalsIgnoreCase("fridges")){
            			
            			if (enabled) {
            				
            				enabled = false;
            				player.sendMessage(ChatColor.AQUA + "Fridges Has Been Disabled!");
            			
            			}else{
            				
            				enabled = true;
            				player.sendMessage(ChatColor.AQUA + "Fridges Has Been Enabled!");
            				
            			}
            		}
            }
            return true;
    }
}