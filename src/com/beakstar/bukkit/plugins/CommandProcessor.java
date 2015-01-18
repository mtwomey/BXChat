package com.beakstar.bukkit.plugins;

import com.sun.tools.classfile.ConstantPool;
import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.security.auth.callback.ConfirmationCallback;

/**
 * Created by mtwomey on 1/17/15.
 */
public class CommandProcessor {
    private String myKey = ConfigFile.getKey();
    private String remoteKey = null;
    private String sourceIP = null;
    private String message = null;

    public void process(String message, String sourceIP) {
        this.sourceIP = sourceIP;
        this.message = message;
        JSONObject jo = null;
        try {
            jo = (JSONObject) new JSONParser().parse(message);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (jo != null){
            remoteKey = (String)jo.get("key");
            String command = jo.get("command").toString();

            if (command.equals("testKey")){
                if (checkKey()) {
                    ConsoleLogger.log("Message received with correct key from: " + sourceIP);
                }
            }

            if (command.equals("broadcast")){
                if (checkKey()){
                    Bukkit.broadcastMessage((String)jo.get("message"));
                }
            }
        }
    }

    private boolean checkKey() {
        if (myKey.equals(remoteKey)){
            return true;
        }
        ConsoleLogger.log("Message received with invalid key from: " + sourceIP);
        ConsoleLogger.log("Message: " + message);
        return false;
    }

}
