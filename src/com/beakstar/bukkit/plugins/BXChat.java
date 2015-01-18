package com.beakstar.bukkit.plugins;

import org.bukkit.Bukkit;
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

        // Advise that the plugin is loaded
        ConsoleLogger.log(this.getName() + " is now enabled");

        ConsoleLogger.log("Server List:");
        for (Server server : ServerList.getServers()){
            ConsoleLogger.log(server.hostname);
        }
    }

    public void loadConfiguration() {
        this.getConfig().addDefault("Servers.server1", "10.10.10.10");
        this.getConfig().addDefault("Servers.server2", "10.10.10.20");
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

    }

}
