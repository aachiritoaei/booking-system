package database;

import java.io.File;
import java.sql.*;

public class SQLiteJDBC {

    //Metoda creeaza baza de date a proiectului
    public void createDataBase() {
        Connection c = null;
        Statement hotel = null;
        Statement room = null;
        Statement reservation = null;
        Statement user = null;
        String sql;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:project.db");
            //Create Hotel Table
            hotel = c.createStatement();
            sql = "CREATE TABLE HOTEL " +
                    "(HOTEL_ID 		INTEGER	PRIMARY KEY	ASC," +
                    " HOTEL_NAME		TEXT    NOT NULL, " +
                    " HOTEL_CAPACITY	INT     NOT NULL, " +
                    " ADDRESS		CHAR(100) 	NOT NULL) ";
            hotel.executeUpdate(sql);
            hotel.close();

            //Create User Table
            user = c.createStatement();
            sql = "CREATE TABLE USER " +
                    "(USER_ID 	INTEGER	PRIMARY KEY	ASC," +
                    " NAME		CHAR(100)    NOT NULL, " +
                    " PASSWORD	CHAR(100)    NOT NULL, " +
                    " USER_NAME	CHAR(100)    NOT NULL, " +
                    " EMAIL		CHAR(100)    NOT NULL, " +
                    " PHONE		CHAR(100)	NOT NULL) ";
            user.executeUpdate(sql);
            user.close();

            //Create Room Table
            room = c.createStatement();
            sql = "CREATE TABLE ROOM " +
                    "(ROOM_ID 	INTEGER	PRIMARY KEY	ASC," +
                    " HOTEL_ID	INT		NOT NULL," +
                    " USER_ID	INT				," +
                    " ROOM_PRICE	INT     NOT NULL, " +
                    " ROOM_CAPACITY	INT	NOT NULL, " +
                    " FOREIGN KEY (HOTEL_ID) REFERENCES HOTEL(HOTEL_ID))";
            room.executeUpdate(sql);
            room.close();

            //Create Reservations Table
            reservation = c.createStatement();
            sql = "CREATE TABLE RESERVATIONS " +
                    "(RESERVATION_ID INTEGER	PRIMARY KEY	ASC," +
                    " ROOM_ID	INT		NOT NULL," +
                    " USER_ID	INT		NOT NULL," +
                    " START_TIME DATE    NOT NULL, " +
                    " END_TIME	DATE	NOT NULL, " +
                    " FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID), " +
                    " FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID))";
            reservation.executeUpdate(sql);
            reservation.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    //Adauga un utilizator in baza de date
    public void addUser(String name, String pass, String uname, String email, String phone) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:project.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO USER  " +
                    "VALUES (NULL, '" + name + "', '" + pass + "', '" + uname + "', '" + email + "', '" + phone + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("User-ul a fost adaugat!");
    }

    //Verifica daca exista deja un user cu username-ul dat ca parametru
    public boolean checkIfUser(String uname) {
        Connection c = null;
        Statement stmt = null;
        boolean flag = false;
        int counter = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:project.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM USER WHERE USER_NAME = '" + uname + "';");
            while (rs.next()) {
                counter++;
            }
            if (counter != 0)
                flag = true;
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return flag;
    }

    //Metoda care creeaza contul utilizatorului.
    public boolean registerUser(String name, String pass, String uname, String email, String phone) {
        if (checkIfUser(uname) == true) {
            System.out.println("ERROR: Username-ul este deja folosit. Va rugam introduceti alt username!");
            return false;
        } else {
            addUser(name, pass, uname, email, phone);
            return true;
        }
    }

    //Metoda intoarce true daca datele de login sunt corecte
    public boolean checkLogin(String uname, String pass) {
        Connection c = null;
        Statement stmt = null;
        boolean flag = false;
        int counter = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:project.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT * FROM USER WHERE USER_NAME = '" + uname + "' AND PASSWORD = '" + pass + "';");
            while (rs.next()) {
                counter++;
            }
            if (counter != 0)
                flag = true;
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return flag;
    }

    public void doLogin(String uname, String pass) {
        if (checkLogin(uname, pass) == true) {
            System.out.println("Datele sunt corecte. Te-ai logat!");
        } else {
            System.out.println("Date gresite. Reintroduce-ti datele!");
        }
    }

    //Afiseaza userii din sistem (doar pentru verificare de functionalitate).
    public void printUsers() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:project.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
            while (rs.next()) {
                int id = rs.getInt("USER_ID");
                String name = rs.getString("NAME");
                String phone = rs.getString("PHONE");
                String uname = rs.getString("USER_NAME");
                String email = rs.getString("EMAIL");
                String pass = rs.getString("PASSWORD");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("UNAME = " + uname);
                System.out.println("PHONE = " + phone);
                System.out.println("EMAIL = " + email);
                System.out.println("PASS = " + pass);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static void main(String args[]) {
        SQLiteJDBC data_base = new SQLiteJDBC();
        File database_name = new File("project.db");
        if (database_name.exists() == false) {
            data_base.createDataBase();
            System.out.println("Penis");
        } else {
            System.out.println("Penis2");
        }
        data_base.registerUser("Petrut", "Pietrificatorul", "Pisti", "pisti@gmail.com", "0754303421");
        data_base.registerUser("Petrut", "Pietrificatorul", "Pisti", "pisti@gmail.com", "0754303421");
        data_base.registerUser("Petrut2", "Pietrificatorul", "Pisti2", "pisti@gmail.com", "0754303421");
        data_base.doLogin("Pisti3", "Pietrificatorul");
        data_base.doLogin("Pisti2", "Pietrificatorul");
        data_base.printUsers();
    }
}
