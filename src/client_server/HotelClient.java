package client_server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Adrian-Claudiu Marinas on 07-Apr-17.
 */

public class HotelClient {

    private Socket socket = null;
    private DataInputStream console = null;
    private DataInputStream streamIn = null;
    private DataOutputStream streamOut = null;

    public HotelClient(String serverName, int serverPort) {
        System.out.println("Establishing connection. Please wait ...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            openStreams();
            runClient();
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        }
    }

    public void openStreams() throws IOException {
        console = new DataInputStream(System.in);
        streamIn = new DataInputStream(new
                BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new
                BufferedOutputStream(socket.getOutputStream()));
    }

    @SuppressWarnings("deprecation")
    public void runClient() {
        while (true) {
            try {
                // read from console and send to server
                streamOut.writeUTF(console.readLine());
                streamOut.flush();

                // wait for response from server
                String line = streamIn.readUTF();
                System.out.println(line);

                if (line.equals(".close")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        HotelClient client = null;
        if (args.length != 2)
            System.out.println("Usage: java HotelClient host port");
        else
            client = new HotelClient(args[0], Integer.parseInt(args[1]));
    }
}
