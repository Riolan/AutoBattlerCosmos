/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.team3.autobattlerserver;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
        
/**
 *
 * @author Rio
 */
public class AutoBattlerServer {
    //static ServerSocket variable
    private static ServerSocket serverSocket;
    //socket server port on which it will listen
    private static int port = 31228;
    
    public AutoBattlerServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    

    public void startServer() {
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
        System.out.println("Starting Server.");
        server.startServer();
    }
    
}