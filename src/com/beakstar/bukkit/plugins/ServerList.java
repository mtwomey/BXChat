package com.beakstar.bukkit.plugins;

import org.bukkit.configuration.ConfigurationSection;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mtwomey on 1/17/15.
 */
public class ServerList {
    private static List<Server> servers = new ArrayList<Server>();

    private ServerList(){ }

    public static List<Server> getServers(){
        return servers;
    }
    public static void addServer(String name, String hostname, int port, String key){
        servers.add(new Server(name, hostname, port, key));
    }

    public static Server getServer(String hostname){
        for (Server server : servers){
            if (server.hostname.equals(hostname)){
                return server;
            }
        }
        return null;
    }

    public static void loadServersFromConfigFile(){
        servers = new ArrayList<Server>();
        ConfigurationSection s = ConfigFile.getServers();
        for (String hostname : ConfigFile.getServers().getKeys(false)){
            ConfigurationSection server = s.getConfigurationSection(hostname);
            addServer(server.getString("name"), hostname, server.getInt("port"), server.getString("key"));
        }
    }
}
