package client_server;

import java.io.Serializable;

/**
 * Created by Claudiu Marinas on 4/23/2017.
 */
public class Room implements Serializable{
    public int hotel_id;
    public int user_id;
    public int price;
    public int capacity;

    public Room() {

    }

    public Room(int hotel_id, int user_id, int price, int capacity) {
        this.hotel_id = hotel_id;
        this.user_id = user_id;
        this.price = price;
        this.capacity = capacity;
    }
}
