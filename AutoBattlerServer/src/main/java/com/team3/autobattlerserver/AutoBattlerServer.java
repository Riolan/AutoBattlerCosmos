/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.team3.autobattlerserver;

import com.team3.autobattlerserver.Client.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rio
 * RIO SAYS HI
 */
public class AutoBattlerServer {
    //static ServerSocket variable
    private static ServerSocket serverSocket;
    //socket server port on which it will listen
    private static int port = 31228;
    
    static Logger logger = Logger.getLogger(AutoBattlerServer.class.getName());

    
    public AutoBattlerServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    

    public void startServer() {
        logger.log( Level.INFO, "Server started.");

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New Client connection.");
                
                // Client Handler
                ClientHandler clientHandler = new ClientHandler(socket);
                
                // Create Thread
                Thread clientThread = new Thread(clientHandler);
                // Start Thread
                clientThread.start();
            }        
        } catch (IOException e) {
            System.out.println("Start Server Error: " + e);
        }
    }
    
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        ServerSocket serverSocket = new ServerSocket(port);
        AutoBattlerServer server = new AutoBattlerServer(serverSocket);
        

        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        logger.addHandler(handlerObj);
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
        
        logger.log(Level.INFO, "Starting server...");

        server.startServer();
        
    }
    
}