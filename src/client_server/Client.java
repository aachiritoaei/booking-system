package client_server; /**
 * Created by Claudiu Marinas on 4/7/2017.
 */

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String argv[]) throws Exception
    {
        Socket clientSocket = new Socket("localhost", 6789);

        String sendMessage;
        String receivedFromServer;

        BufferedReader inputFromClient = new BufferedReader( new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        sendMessage = inputFromClient.readLine();
        outToServer.writeBytes(sendMessage + '\n');

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        receivedFromServer = inFromServer.readLine();
        System.out.println("FROM SERVER: " + receivedFromServer);

        clientSocket.close();
    }
}
