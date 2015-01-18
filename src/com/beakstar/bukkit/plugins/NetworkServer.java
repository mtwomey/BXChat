package com.beakstar.bukkit.plugins;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mtwomey on 1/17/15.
 */
public class NetworkServer {
    private ServerSocket listener = null;
    private Socket socket = null;
    private Boolean listen = true;
    private NetworkCommandProcessor networkCommandProcessor = new NetworkCommandProcessor();

    private Runnable listenerThread = new Runnable() {
        @Override
        public void run() {
            try {
                while (listen) {
                    socket = listener.accept();
                    networkCommandProcessor.process(readFromSocket(), socket.getInetAddress().getHostAddress());
                    socket.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    assert listener != null;
                    listener.close();
                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    };
    private Thread serverThread = new Thread(listenerThread);

    public void listen(int port){
        ConsoleLogger.log("Starting listener on port " + port);
        try {
            this.listener = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverThread.start();
    }

    private String readFromSocket() {
        char[] buffer1 = new char[1000];
        try {
            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            InputStreamReader isr = new InputStreamReader(bis);
            BufferedReader br = new BufferedReader(isr);  // My FUCKING LORD - I hate Java, I mean look at this!
            br.read(buffer1, 0, 1000);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new String(buffer1).trim();
    }
}
