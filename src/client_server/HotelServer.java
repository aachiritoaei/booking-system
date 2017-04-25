package client_server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import database.SQLiteJDBC;

/**
 * Created by Adrian-Claudiu Marinas on 07-Apr-17.
 */

public class HotelServer {

    // clients
    private HotelServerThread clients[] = new HotelServerThread[8];
    private ServerSocket server = null;
    private int clientCount = 0;
    public SQLiteJDBC sqlDataBase;

    public HotelServer(int port) {
        try {
            sqlDataBase = new SQLiteJDBC();
            File database_name = new File("project.db");
            if(database_name.exists() == false){
                sqlDataBase.createDataBase();
                System.out.println("DB nu exista");
            } else {
                System.out.println("DB exista");
            }

            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);

            runServer();
        } catch (IOException ioe) {
            System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
        }
    }

    // start listening
    // when a client connects, create a thread for him and add him to connected clients
    public void runServer() {
        while (true) {
            try {
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            } catch (IOException ioe) {
                System.out.println("Server accept error: " + ioe);
                break;
            }
        }
    }

    // add a client-thread to the current clients
    private void addThread(Socket socket) {
        if (clientCount < clients.length) {
            System.out.println("HotelClient accepted: " + socket);
            clients[clientCount] = new HotelServerThread(this, socket);
            clientCount++;
        } else
            System.out.println("HotelClient refused: maximum " + clients.length + " reached.");
    }

    // remove a client-thread from the current clients
    @SuppressWarnings("deprecation")
    public synchronized void removeThread(int ID) {
        int pos = findClient(ID);
        if (pos >= 0) {
            HotelServerThread toTerminate = clients[pos];
            System.out.println("Removing client thread " + ID + " at " + pos);

            if (pos < clientCount - 1) {
                for (int i = pos + 1; i < clientCount; i++) {
                    clients[i - 1] = clients[i];
                }
            }

            clientCount--;

            try {
                toTerminate.close();
            } catch (IOException ioe) {
                System.out.println("Error closing thread: " + ioe);
            }
            toTerminate.stop();
        }
    }

    // find a client corresponding to its thread id
    public int findClient(int ID) {
        for (int i = 0; i < clientCount; i++) {
            if (clients[i].getID() == ID) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String args[]) {
        HotelServer server = null;
        if (args.length != 1)
            System.out.println("Usage: java HotelServer port");
        else
            server = new HotelServer(Integer.parseInt(args[0]));
    }
}
