package client_server;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Adrian-Claudiu Marinas on 07-Apr-17.
 */

public class HotelServerThread extends Thread {

    private HotelServer server = null;
    private Socket socket = null;
    private int ID = -1;
    private DataInputStream streamIn = null;
    private DataOutputStream streamOut = null;
    private ObjectInputStream serverInputStream = null;
    private ObjectOutputStream serverOutputStream = null;

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

    public void openSerializable() throws IOException {
        serverInputStream = new
                ObjectInputStream(socket.getInputStream());
        serverOutputStream = new
                ObjectOutputStream(socket.getOutputStream());
    }

    @SuppressWarnings("deprecation")
    public void run() {
        System.out.println("Server thread " + ID + " running.");

        // handle other commands
        while (true) {
            try {
                handleClientCommand(streamIn.readUTF());
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.removeThread(ID);
                stop();
            }
        }
    }

    public static int caseByCommand(String command) {
        int res = 0;
        if (command.equals("Login")) {
            res = 0;
        } else if (command.equals("Register")) {
            res = 1;
        } else if (command.equals("Hotel")) {
            res = 2;
        } else if (command.equals("Room")) {
            res = 3;
        } else if(command.equals("Search")) {
            res = 4;
        } else {
            res = 5;
        }
        return res;
    }

    public void handleClientCommand(String input) {
        send(input);
        String[] commands = input.split(" ");

        int sCase = caseByCommand(commands[0]);
        // received from user
        switch (sCase) {
            // Login
            case 0:
                // receive
                try {
                    openSerializable();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Login newSession = null;
                try {
                    newSession  = (Login)serverInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    serverOutputStream.writeObject(newSession);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(newSession.uname + " " + newSession.pass);

                // interogare BD
                server.sqlDataBase.doLogin(newSession.uname, newSession.pass);
                break;
            // Register
            case 1:
                // receive
                try {
                    openSerializable();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Register newUser = null;
                try {
                    newUser  = (Register)serverInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    serverOutputStream.writeObject(newUser);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(newUser.name + " " + newUser.pass + " " + newUser.uname + " " + newUser.email + " " + newUser.phone);

                // interogare BD
                server.sqlDataBase.addUser(newUser.name, newUser.pass, newUser.uname, newUser.email, newUser.phone);
                break;
            // Hotel
            case 2:
                // receive
                try {
                    openSerializable();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Hotel newHotel = null;
                try {
                    newHotel  = (Hotel) serverInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    serverOutputStream.writeObject(newHotel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(newHotel.name + " " + newHotel.capacity + " " + newHotel.address);
                // interogare BD
                server.sqlDataBase.addHotel(newHotel.name, newHotel.capacity, newHotel.address);
                break;
            // Room
            case 3:
                // receive
                try {
                    openSerializable();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Room newRoom = null;
                try {
                    newRoom  = (Room) serverInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    serverOutputStream.writeObject(newRoom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(newRoom.hotel_id + " " + newRoom.user_id + " " + newRoom.price + " " + newRoom.capacity);
                // interogare BD
                server.sqlDataBase.addRoom(newRoom.hotel_id, newRoom.user_id, newRoom.price, newRoom.capacity);
                break;
            // Search
            case 4:
                // receive
                try {
                    openSerializable();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Search newSearch = null;
                try {
                    newSearch  = (Search) serverInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    serverOutputStream.writeObject(newSearch);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(newSearch.capacity + " " + newSearch.price + " " + newSearch.address + " " + newSearch.name);
                // interogare BD
                ArrayList<String> searchResult = server.sqlDataBase.getRoomsBySearch(newSearch.capacity, newSearch.cap_flag,
                        newSearch.price, newSearch.cap_flag,
                        newSearch.address, newSearch.address_flag,
                        newSearch.name, newSearch.name_flag);

                System.out.println(searchResult.toString());
                break;
            case 5:
                break;
        }

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
