package com.beakstar.bukkit.plugins;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.json.simple.JSONObject;

/**
 * Created by mtwomey on 1/16/15.
 */
public class RelayDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        for (Server server : ServerList.getServers()){
            JSONObject jo = new JSONObject();
            jo.put("command", "broadcast");
            jo.put("message", event.getDeathMessage());
            jo.put("key", server.key);
            NetworkClient.send(server.hostname, server.port, jo.toJSONString());
        }
    }
}
