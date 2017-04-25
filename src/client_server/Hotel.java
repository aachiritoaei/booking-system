package client_server;

import java.io.Serializable;

/**
 * Created by Claudiu Marinas on 4/23/2017.
 */
public class Hotel implements Serializable{
    public String name;
    public int capacity;
    public String address;

    public Hotel() {

    }

    public Hotel(String name, int capacity, String address) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

}
