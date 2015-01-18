package com.beakstar.bukkit.plugins;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by mtwomey on 1/17/15.
 */
public class NetworkClient {

    private NetworkClient() { } // Prevent it from being instantiated

    public static void send(String hostname, int port, String message) {
        NetworkSend networkSend = new NetworkClient().new NetworkSend(hostname, port, message);
        Thread serverThread = new Thread(networkSend);
        serverThread.start();
    }

    private class NetworkSend implements Runnable {
        String hostname;
        int port;
        String message;

        NetworkSend(String hostname, int port, String message) {
            this.hostname = hostname;
            this.port = port;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket(hostname, port);
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.print(message);
                socket.close();
            } catch (IOException e) {
                ConsoleLogger.log("Server in chat network not responding: " + hostname);
            }
        }
    }

}

