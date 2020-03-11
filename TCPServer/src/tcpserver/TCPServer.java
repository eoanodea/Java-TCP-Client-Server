/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.*;
import java.net.*;

/**
 *
 * @author n00162393
 */
public class TCPServer {
    static int portNum = 6788;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
 
        String clientSentence;
        String capitalizedSentence;
        
        ServerSocket welcomeSocket = new ServerSocket(portNum);
        
        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream())
            );

            DataOutputStream outToClient = new DataOutputStream(
                    connectionSocket.getOutputStream()
            );
            
          clientSentence = inFromClient.readLine();
          
          capitalizedSentence = getResponse(clientSentence);
        
          System.out.println("Sending message to client" + capitalizedSentence);
            
     
          outToClient.writeBytes(capitalizedSentence + "\n");
        }
    }
    
    private static String getResponse(String msg) {
        System.out.println("Running response");
        String response;
        
            switch(msg) {
                case"HELLO":
                    response = "Hi there";
                    break;
                
                case "PORT":
                    response = "Port Number: " + Integer.toString(portNum);
                    break;
                default: 
                    response = "INVALID REQUEST";
            }
                
        System.out.println("Returning response" + response);
        return response;
    }
}
