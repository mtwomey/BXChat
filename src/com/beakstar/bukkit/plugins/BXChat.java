package com.beakstar.bukkit.plugins;

import org.bukkit.*;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by mtwomey on 1/16/15.
 */
public class BXChat extends JavaPlugin {

    public void onDisable() {
        ConsoleLogger.log("Chat1 is now disabled!");
    }

    public void onEnable() {
        // Setup the console logger so others can use it
        ConsoleLogger.setLogger(this.getLogger());

        // Create a default configuration if there isn't one already
        this.saveDefaultConfig();

        // Custom config file handles to avoid issues with parsing IP addresses with '.' as default separator for YAML
        // You can't change the separator for the regular handler until after the config is already loaded - blabla
        ConfigFile.setConfigFile(new File(this.getDataFolder(), "config.yml"));

        // Start the network server
        new NetworkServer().listen(ConfigFile.getListenPort());

        // Load up the participating server list from the config file
        ServerList.loadServersFromConfigFile();

        // Register the chat hook
        Bukkit.getPluginManager().registerEvents(new RelayChat(), this);
        // Register the login hook
        Bukkit.getPluginManager().registerEvents(new RelayLogin(), this);
        // Register the logout hook
        Bukkit.getPluginManager().registerEvents(new RelayLogOut(), this);
        // Register the death hook
        Bukkit.getPluginManager().registerEvents(new RelayDeath(), this);
        // Register the achievement hook
        Bukkit.getPluginManager().registerEvents(new RelayAchievement(), this);

        Bukkit.getPluginManager().registerEvents(new CommandOverrides(), this);

        // Add bcxhat command
        // This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
        this.getCommand("bxchat").setExecutor(new CommandProcessor(this));

        // List out the participating servers on startup
        ServerList.listServersToConsole();

        // Advise that the plugin is loaded
        ConsoleLogger.log(this.getName() + " is now enabled");

    }

    public void loadConfiguration() {
        this.getConfig().addDefault("Servers.server1", "10.10.10.10");
        this.getConfig().addDefault("Servers.server2", "10.10.10.20");
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

    }

}
