package com.beakstar.bukkit.plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by mtwomey on 1/16/15.
 */
public class ConsoleLogger {
    private static ConsoleLogger instance = null;
    private static Logger logger = null;

//    protected ConsoleLogger(){
//
//    }
//
//    public static ConsoleLogger getInstance(){
//        if (instance == null){
//            instance = new ConsoleLogger();
//        }
//        return instance;
//    }

    public static void setLogger(Logger l){
        logger = l;
    }

    public static void log(String message){
        logger.info(message);
    }
}
