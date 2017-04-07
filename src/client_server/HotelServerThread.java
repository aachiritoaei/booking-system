package client_server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Adrian-Claudiu Marinas on 07-Apr-17.
 */

public class HotelServerThread extends Thread {

    private HotelServer server = null;
    private Socket socket = null;
    private int ID = -1;
    private DataInputStream streamIn = null;
    private DataOutputStream streamOut = null;

    public HotelServerThread(HotelServer server, Socket socket) {
        super();
        this.server = server;
        this.socket = socket;
        ID = socket.getPort();

        try {
            openStreams();
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openStreams() throws IOException {
        streamIn = new DataInputStream(new
                BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new
                BufferedOutputStream(socket.getOutputStream()));
    }

    @SuppressWarnings("deprecation")
    public void run() {
        System.out.println("Server thread " + ID + " running.");

        // handle other commands
        while (true) {
            try {
                handleClientCommand(streamIn.readUTF());
            } catch (IOException ioe) {
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.removeThread(ID);
                stop();
            }
        }
    }

    public void handleClientCommand(String input) {
        String[] commands = input.split(" ");
        send(input);
    }

    @SuppressWarnings("deprecation")
    public void send(String msg) {
        try {
            streamOut.writeUTF(msg);
            streamOut.flush();
        } catch (IOException ioe) {
            System.out.println(ID + " ERROR sending: " + ioe.getMessage());
            server.removeThread(ID);
            stop();
        }
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (streamIn != null) streamIn.close();
        if (streamOut != null) streamOut.close();
    }

    public int getID() {
        return ID;
    }
}
