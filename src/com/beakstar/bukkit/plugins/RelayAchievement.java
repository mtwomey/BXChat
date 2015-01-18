package com.beakstar.bukkit.plugins;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.json.simple.JSONObject;

/**
 * Created by mtwomey on 1/16/15.
 */
public class RelayAchievement implements Listener {
    @EventHandler
    public void onPlayerAchievement(PlayerAchievementAwardedEvent event) {
        String achievementName = event.getAchievement().name();
        String playerName = event.getPlayer().getName();
        String worldName = event.getPlayer().getWorld().getName();

        for (Server server : ServerList.getServers()){
            JSONObject jo = new JSONObject();
            jo.put("command", "broadcast");
            jo.put("message", ChatColor.YELLOW + "Player " + playerName + "(" + worldName + ") has just earned the achievement [" + achievementName + "]");
            jo.put("key", server.key);
            NetworkClient.send(server.hostname, server.port, jo.toJSONString());
        }
    }
}
