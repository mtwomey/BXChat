package com.beakstar.bukkit.plugins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by mtwomey on 1/18/15.
 */
public class CommandOverrides implements Listener {
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e){
        String command = e.getMessage();

        if (command.equalsIgnoreCase("/list")){
            // Do my thing here... show players from other servers.
        }

    }

}
