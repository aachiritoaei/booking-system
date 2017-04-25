package database;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC {

	//Metoda creeaza baza de date a proiectului
	public void createDataBase()
	{
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
	    	sql = "CREATE TABLE RESERVATION " +
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
	    }catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
	
	//Adauga un utilizator in baza de date
	public void addUser(String name, String pass, String uname, String email, String phone)
	{
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
		 }catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     System.exit(0);
		 }
		 System.out.println("User-ul a fost adaugat!");
	}
	
	//Adauga o camera in BD
	public void addRoom(int hotel_id, int user_id, int price, int capacity)
	{
		Connection c = null;
		Statement stmt = null;
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:project.db");
		      c.setAutoCommit(false);

		      stmt = c.createStatement();
		      String sql = "INSERT INTO ROOM  " +
		                   "VALUES (NULL, '" + hotel_id + "', '" + user_id + "', '" + price + "', '" + capacity + "');"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.commit();
		      c.close();
		 }catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     System.exit(0);
		 }
		 System.out.println("Camera a fost adaugata!");
	}
	
	//Adauga o rezervare in BD, timpul trebuie trimis la string in format mm/dd/yyyy
	public void addReservation(int room_id, int user_id, String start_time, String end_time)
	{
		Connection c = null;
		Statement stmt = null;
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:project.db");
		      c.setAutoCommit(false);

		      stmt = c.createStatement();
		      String sql = "INSERT INTO RESERVATION  " +
		                   "VALUES (NULL, '" + room_id + "', '" + user_id + "', '" + start_time + "', '" + end_time + "');"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.commit();
		      c.close();
		 }catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     System.exit(0);
		 }
		 System.out.println("Rezervarea a fost adaugata!");
	}
	
	//Adauga un hotel in BD
	public void addHotel(String name, int capacity, String address)
	{
		Connection c = null;
		Statement stmt = null;
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:project.db");
		      c.setAutoCommit(false);

		      stmt = c.createStatement();
		      String sql = "INSERT INTO HOTEL  " +
		                   "VALUES (NULL, '" + name + "', '" + capacity + "', '" + address + "');"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.commit();
		      c.close();
		 }catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     System.exit(0);
		 }
		 System.out.println("Hotelul a fost adaugat!");
	}
	
	//Verifica daca exista deja un user cu username-ul dat ca parametru
	public boolean checkIfUser(String uname)
	{
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
	      rs = stmt.executeQuery( "SELECT * FROM USER WHERE USER_NAME = '" + uname + "';" );
	      while(rs.next()) {
	    	  counter++;
	      }
	      if(counter != 0)
	    	  flag = true;
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return flag;
	}
	
	//Metoda care creeaza contul utilizatorului.
	public boolean registerUser(String name, String pass, String uname, String email, String phone)
	{
		if (checkIfUser(uname) == true) {
			System.out.println("ERROR: Username-ul este deja folosit. Va rugam introduceti alt username!");
			return false;
		} else {
			addUser(name, pass, uname, email, phone);
			return true;
		}
	}
	
	//Metoda intoarce true daca datele de login sunt corecte
	public boolean checkLogin(String uname, String pass)
	{
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
	      rs = stmt.executeQuery( "SELECT * FROM USER WHERE USER_NAME = '" + uname + "' AND PASSWORD = '" + pass + "';" );
	      while(rs.next()) {
	    	  counter++;
	      }
	      if(counter != 0)
	    	  flag = true;
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return flag;
	}
	
	public void doLogin(String uname, String pass)
	{
		if (checkLogin(uname, pass) == true) {
			System.out.println("Datele sunt corecte. Te-ai logat!");
		} else {
			System.out.println("Date gresite. Reintroduce-ti datele!");
		}
	}
	
	//Afiseaza userii din sistem (doar pentru verificare de functionalitate).
	public void printUsers()
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM USER;" );
	      while ( rs.next() ) {
	         int id = rs.getInt("USER_ID");
	         String  name = rs.getString("NAME");
	         String phone  = rs.getString("PHONE");
	         String  uname = rs.getString("USER_NAME");
	         String email = rs.getString("EMAIL");
	         String pass = rs.getString("PASSWORD");
	         System.out.println( "ID = " + id );
	         System.out.println( "NAME = " + name );
	         System.out.println( "UNAME = " + uname );
	         System.out.println( "PHONE = " + phone );
	         System.out.println( "EMAIL = " + email );
	         System.out.println( "PASS = " + pass );
	         System.out.println();
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	}
	
	public void printHotels()
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM HOTEL;" );
	      while ( rs.next() ) {
	         int id = rs.getInt("HOTEL_ID");
	         String  name = rs.getString("HOTEL_NAME");
	         int cap  = rs.getInt("HOTEL_CAPACITY");
	         String  address = rs.getString("ADDRESS");
	         System.out.println( "ID = " + id );
	         System.out.println( "NAME = " + name );
	         System.out.println( "CAP = " + cap );
	         System.out.println( "ADDRESS = " + address );
	         System.out.println();
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	}
	
	public void printRoom()
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ROOM;" );
	      while ( rs.next() ) {
	         int id = rs.getInt("ROOM_ID");
	         int hotel = rs.getInt("HOTEL_ID");
	         int user = rs.getInt("USER_ID");
	         int price = rs.getInt("ROOM_PRICE");
	         int cap  = rs.getInt("ROOM_CAPACITY");
	         System.out.println( "ID = " + id );
	         System.out.println( "ID_HOT = " + hotel );
	         System.out.println( "id_USER = " + user );
	         System.out.println( "PRICE = " + price );
	         System.out.println( "CAP = " + cap );
	         System.out.println();
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	}
	
	public void printReservation()
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM RESERVATION;" );
	      
	      while ( rs.next() ) {
	         int id = rs.getInt("RESERVATION_ID");
	         int room = rs.getInt("ROOM_ID");
	         int user = rs.getInt("USER_ID");
	         String date1 = rs.getString("START_TIME");
	         String date2 = rs.getString("END_TIME");
	         System.out.println( "ID = " + id );
	         System.out.println( "ID_HOT = " + room );
	         System.out.println( "id_USER = " + user );
	         System.out.println( "date_start = " + date1 );
	         System.out.println( "date_end = " + date2 );
	         System.out.println();
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	}
	
	public void deleteUser(int id)
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "DELETE from USER where USER_ID=" + id + ";";
	      stmt.executeUpdate(sql);
	      c.commit();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Delete done successfully");
	}
	
	public void deleteRoom(int id)
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "DELETE from ROOM where ROOM_ID=" + id + ";";
	      stmt.executeUpdate(sql);
	      c.commit();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Delete done successfully");
	}
	
	//Returneaza numele hotelului care are primary key = id
	public String getHotel(int id)
	{
		String res = "";
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM HOTEL WHERE HOTEL_ID = " + id + ";" );
	      rs.next();
	      res = rs.getString("HOTEL_NAME");
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return res;
	}
	
	//Returneaza adresa hotelului care are PK = id
	public String getAddress(int id)
	{
		String res = "";
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM HOTEL WHERE HOTEL_ID = " + id + ";" );
	      rs.next();
	      res = rs.getString("ADDRESS");
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return res;
	}
	
	//Returneaza o lista cu toate camerele din sistem
	public ArrayList<String> getAllRooms()
	{
		ArrayList<String> res = new ArrayList<String>();
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ROOM;" );
	      while ( rs.next() ) {
	    	  String part = "";
	    	  part += rs.getInt("ROOM_ID");
	    	  part += "    ";
	    	  part += getHotel(rs.getInt("HOTEL_ID"));
	    	  part += "    ";
	    	  part += rs.getInt("ROOM_CAPACITY");
	    	  part += "    ";
	    	  part += rs.getInt("ROOM_PRICE");
	    	  part += "    ";
	    	  part += getAddress(rs.getInt("HOTEL_ID"));
	    	  res.add(part);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return res;
	}
	
	//Returnez o lista cu toate camerele care satisfac
	//conditiile. Flag = 1 inseamna ca se filtreaza
	//dupa argumentul respectiv.
	public ArrayList<String> getRoomsBySearch(int capacity,
			int cap_flag, int price, int price_flag,
			String address, int address_flag,
			String name, int name_flag)
	{
		ArrayList<String> res = new ArrayList<String>();
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ROOM;" );
	      while ( rs.next() ) {
	    	  int ok = 0;
	    	  String part = "";
	    	  part += rs.getInt("ROOM_ID");
	    	  part += "    ";
	    	  part += getHotel(rs.getInt("HOTEL_ID"));
	    	  part += "    ";
	    	  part += rs.getInt("ROOM_CAPACITY");
	    	  part += "    ";
	    	  part += rs.getInt("ROOM_PRICE");
	    	  part += "    ";
	    	  part += getAddress(rs.getInt("HOTEL_ID"));
	    	  if (cap_flag == 1)
	    		  if (rs.getInt("ROOM_CAPACITY") != capacity)
	    			  ok = 1;
	    	  if (price_flag == 1)
	    		  if (rs.getInt("ROOM_PRICE") > price)
	    			  ok = 1;
	    	  if (name_flag == 1)
	    		  if (getHotel(rs.getInt("HOTEL_ID")).equals(name) == false)
	    			  ok = 1;
	    	  if (address_flag == 1)
	    		  if (getAddress(rs.getInt("HOTEL_ID")).equals(address) == false)
	    			  ok = 1;
	    	  if (ok == 0)
	    		  res.add(part);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return res;
	}
	
	public void deleteHotel(int id)
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "DELETE from HOTEL where HOTEL_ID=" + id + ";";
	      stmt.executeUpdate(sql);
	      c.commit();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Delete done successfully");
	}
	
	public void deleteReservation(int id)
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:project.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "DELETE from RESERVATION where RESERVATION_ID=" + id + ";";
	      stmt.executeUpdate(sql);
	      c.commit();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Delete done successfully");
	}
	
	public static void main (String args[])
	{
		SQLiteJDBC data_base = new SQLiteJDBC();
		File database_name = new File("project.db");
		if(database_name.exists() == false){
			data_base.createDataBase();
			System.out.println("DB nu exista");
		} else {
			System.out.println("DB exista");
		}
		data_base.registerUser("Petrut", "Pietrificatorul", "Pisti", "pisti@gmail.com", "0754303421");
		data_base.registerUser("Petrut", "Pietrificatorul", "Pisti", "pisti@gmail.com", "0754303421");
		data_base.registerUser("Petrut2", "Pietrificatorul", "Pisti2", "pisti@gmail.com", "0754303421");
		data_base.doLogin("Pisti3", "Pietrificatorul");
		data_base.doLogin("Pisti2", "Pietrificatorul");
		data_base.printUsers();
		//data_base.deleteUser(1);
		//data_base.printUsers();
		data_base.addHotel("Cristi", 110, "Aici");
		data_base.printHotels();
		//data_base.deleteHotel(1);
		//data_base.printHotels();
		data_base.addRoom(1, 1, 100, 100);
		data_base.printRoom();
		//data_base.deleteRoom(1);
		//data_base.printRoom();
		data_base.addReservation(1, 1, "2006-06-06", "2006-06-10");
		data_base.printReservation();
		//data_base.deleteReservation(1);
		//data_base.printReservation();
		ArrayList<String> res = data_base.getRoomsBySearch(100, 1, 120, 1, "Aici", 1, "Cristi", 1);
		for(int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}
}
