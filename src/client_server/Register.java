package client_server;

import java.io.Serializable;

/**
 * Created by Claudiu Marinas on 4/23/2017.
 */
public class Register implements Serializable{
    public String name;
    public String pass;
    public String uname;
    public String email;
    public String phone;

    public Register() {

    }

    public Register(String name, String pass, String uname, String email, String phone) {
        this.name = name;
        this.pass = pass;
        this.uname = uname;
        this.email = email;
        this.phone = phone;
    }
}
