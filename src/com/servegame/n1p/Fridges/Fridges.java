package com.servegame.n1p.Fridges;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Fridges extends JavaPlugin {

    Logger log = Logger.getLogger("Minecraft");
    public boolean enabled;
    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
                .getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }

    public void onEnable() {
        enabled = true;
        this.saveDefaultConfig();
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new FridgesPlayerListener(this), this);
        log.info(this.getDescription().getName() + " - Version " + this.getDescription().getVersion()
                + " has been enabled.");
        setupEconomy();
    }

    public void onDisable() {
        log.info(this.getDescription().getName() + " - Version " + this.getDescription().getVersion()
                + " has been disabled.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (commandLabel.equalsIgnoreCase("fridges") && args.length == 0) {
                if (enabled) {
                    enabled = false;
                    player.sendMessage(ChatColor.AQUA + "Fridges has been disabled!");
                } else {
                    enabled = true;
                    player.sendMessage(ChatColor.AQUA + "Fridges has been enabled!");
                }
            } else if (commandLabel.equalsIgnoreCase("fridges") && args[0].equalsIgnoreCase("reload")) {
                this.reloadConfig();
                player.sendMessage(ChatColor.AQUA + "Fridges has been reloaded!");
            }
        }
        return true;
    }
}