package client_server;

import java.io.Serializable;

/**
 * Created by Claudiu Marinas on 4/24/2017.
 */
public class Search implements Serializable{
    int capacity;
    int cap_flag;
    int price;
    int price_flag;
    String address;
    int address_flag;
    String name;
    int name_flag;

    public Search() {

    }

    public Search(int capacity, int cap_flag, int price, int price_flag, String address, int address_flag, String name, int name_flag) {
        this.capacity = capacity;
        this.cap_flag = cap_flag;
        this.price = price;
        this.price_flag = price_flag;
        this.address = address;
        this.address_flag = address_flag;
        this.name = name;
        this.name_flag = name_flag;
    }
}
