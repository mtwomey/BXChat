package com.beakstar.bukkit.plugins;

/**
 * Created by mtwomey on 1/17/15.
 */
public class Server {
    public String hostname;
    public String name;
    public int port;
    public String key;

    public Server(String name, String hostname, int port, String key){
        this.name = name;
        this.hostname = hostname;
        this.port = port;
        this.key = key;
    }
}
