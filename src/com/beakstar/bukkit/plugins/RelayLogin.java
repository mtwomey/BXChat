package com.beakstar.bukkit.plugins;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.json.simple.JSONObject;

/**
 * Created by mtwomey on 1/16/15.
 */
public class RelayLogin implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        String playerDisplayName = event.getPlayer().getDisplayName();
        String worldName = event.getPlayer().getWorld().getName();
        for (Server server : ServerList.getServers()){
            JSONObject jo = new JSONObject();
            jo.put("command", "broadcast");
            jo.put("message", ChatColor.YELLOW + playerDisplayName + " joined the game (currently in " + ChatColor.RED + worldName + ChatColor.YELLOW + ").");
            jo.put("key", server.key);
            NetworkClient.send(server.hostname, server.port, jo.toJSONString());
        }
    }
}
