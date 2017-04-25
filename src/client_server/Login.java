package client_server;

import sun.rmi.runtime.Log;

import java.io.Serializable;

/**
 * Created by Claudiu Marinas on 4/23/2017.
 */
public class Login implements Serializable{
    public String uname;
    public String pass;

    public Login() {

    }

    public Login(String uname, String pass) {
        this.uname = uname;
        this.pass = pass;
    }
}
