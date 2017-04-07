package client_server; /**
 * Created by Claudiu Marinas on 4/7/2017.
 */

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String argv[]) throws Exception {
        String receivedFromClient;
        String sendMessage;
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            receivedFromClient = inFromClient.readLine();

            System.out.println("Received: " + receivedFromClient);

            sendMessage = "OK\n";
            outToClient.writeBytes(sendMessage);
        }
    }
}
