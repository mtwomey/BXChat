package com.beakstar.bukkit.plugins;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by mtwomey on 1/18/15.
 */
public class CommandProcessor implements CommandExecutor {
    private final BXChat bxChat;

    public CommandProcessor(BXChat bxChat) {
        this.bxChat = bxChat;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        // Note - I could check if the command was from console or player with
        // if (sender instanceof Player) {
        // It will be Player or ConsoleCommandSender
        if (command.getName().equalsIgnoreCase("bxchat") & args.length > 0){

            if (args[0].equalsIgnoreCase("list")){
                if (commandSender instanceof Player){
                    ServerList.listServersToPlayer((Player)commandSender);
                } else {
                    ServerList.listServersToConsole();
                }
            }

            if (args[0].equalsIgnoreCase("reload")){
                ConfigFile.loadConfigFile();
                ServerList.loadServersFromConfigFile();
                if (commandSender instanceof Player){
                    commandSender.sendMessage("Server list reloaded from config file.");
                    ServerList.listServersToPlayer((Player)commandSender);
                } else {
                    ConsoleLogger.log("Server list reloaded from config file.");
                    ServerList.listServersToConsole();
                }
            }

            return true;
        }
        return false;
    }
}
