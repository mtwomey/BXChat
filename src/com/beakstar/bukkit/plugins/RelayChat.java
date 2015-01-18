package com.beakstar.bukkit.plugins;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.json.simple.JSONObject;

/**
 * Created by mtwomey on 1/16/15.
 */
public class RelayChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String chatMessage = event.getMessage();
        String playerDisplayName = event.getPlayer().getDisplayName();
        String playerName = event.getPlayer().getName();
        String worldName = event.getPlayer().getWorld().getName();

        for (Server server : ServerList.getServers()){
            JSONObject jo = new JSONObject();
            jo.put("command", "broadcast");
            jo.put("message", ChatColor.WHITE + "[" + worldName + "]" + playerName + ChatColor.DARK_GREEN+ ": " + chatMessage);
            jo.put("key", server.key);
            NetworkClient.send(server.hostname, server.port, jo.toJSONString());
        }
    }
}
