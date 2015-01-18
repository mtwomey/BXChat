package com.beakstar.bukkit.plugins;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by mtwomey on 1/17/15.
 */
public class ConfigFile {
    private static YamlConfiguration yamlConfiguration;
    private static File configFile;

    public static void setConfigFile(File f){
        configFile = f;
        loadConfigFile();
    }

    public static void loadConfigFile(){
        yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.options().pathSeparator('|');
        try {
            yamlConfiguration.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getConfig(){
        return yamlConfiguration;
    }

    public static ConfigurationSection getServers(){
        return ConfigFile.getConfig().getConfigurationSection("servers");
    }

    public static int getListenPort(){
        return getConfig().getInt("listenPort");
    }

    public static String getKey() {
        return getConfig().getString("key");
    }
}
