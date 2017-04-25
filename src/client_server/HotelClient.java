package client_server;

import java.io.*;
import java.net.InterfaceAddress;
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
    private ObjectInputStream clientInputStream = null;
    private ObjectOutputStream clientOutputStream = null;

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

    public void openSerializable() throws IOException {
        clientOutputStream = new
                ObjectOutputStream(socket.getOutputStream());
        clientInputStream = new
                ObjectInputStream(socket.getInputStream());
    }

    @SuppressWarnings("deprecation")
    public void runClient() {
        while (true) {
            try {
                // read from console and send to server
                String command = console.readLine();
                streamOut.writeUTF(command);
                streamOut.flush();

                // wait for response from server
                String line = streamIn.readUTF();
                System.out.println(line);

                // do object
                int sCase = HotelServerThread.caseByCommand(command);
                switch (sCase) {
                    case 0:
                        System.out.print("Uname: ");
                        String uname = console.readLine();
                        System.out.print("Pass: ");
                        String pass = console.readLine();

                        openSerializable();
                        Login newSession = new Login(uname, pass);
                        clientOutputStream.writeObject(newSession);

                        try {
                            newSession = (Login)clientInputStream.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 1:
                        System.out.print("Name: ");
                        String name = console.readLine();
                        System.out.print("Pass: ");
                        pass = console.readLine();
                        System.out.print("Uname: ");
                        uname = console.readLine();
                        System.out.print("Email: ");
                        String email = console.readLine();
                        System.out.print("Phone: ");
                        String phone = console.readLine();
                        openSerializable();

                        Register newUser = new Register(name, pass, uname, email, phone);
                        clientOutputStream.writeObject(newUser);

                        try {
                            newUser = (Register)clientInputStream.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        System.out.print("Name of hotel: ");
                        name = console.readLine();
                        System.out.print("Capacity of hotel: ");
                        int capacity = Integer.parseInt(console.readLine());
                        System.out.print("Address of hotel: ");
                        String addr = console.readLine();
                        openSerializable();

                        Hotel newHotel = new Hotel(name, capacity, addr);
                        clientOutputStream.writeObject(newHotel);

                        try {
                            newHotel = (Hotel)clientInputStream.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.print("Id hotel: ");
                        int hotel_id = Integer.parseInt(console.readLine());
                        System.out.print("Id user: ");
                        int user_id = Integer.parseInt(console.readLine());
                        System.out.print("Price: ");
                        int price = Integer.parseInt(console.readLine());
                        System.out.print("Capacity: ");
                        capacity = Integer.parseInt(console.readLine());
                        openSerializable();

                        Room newRoom = new Room(hotel_id, user_id, price, capacity);
                        clientOutputStream.writeObject(newRoom);

                        try {
                            newRoom = (Room)clientInputStream.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        int capacity_flag = 0, price_flag = 0, addr_flag = 0, name_flag = 0;
                        System.out.print("Capacity: ");
                        capacity = Integer.parseInt(console.readLine());
                        System.out.print("Price: ");
                        price = Integer.parseInt(console.readLine());
                        System.out.print("Address: ");
                        addr = console.readLine();
                        System.out.print("Name: ");
                        name = console.readLine();
                        openSerializable();

                        Search newSearch = new Search(capacity, capacity_flag,
                                price, price_flag,
                                addr, addr_flag,
                                name, name_flag);
                        clientOutputStream.writeObject(newSearch);

                        try {
                            newSearch = (Search)clientInputStream.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        break;
                }

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
