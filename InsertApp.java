package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class InsertApp {

    /**
     * Connect to the MOVIES.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/MOVIES.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the insertapp table
     *
     * @param name
     * @param capacity
     */
    public void insert(String Name,String Actor,String Actress,String Director,int Year_Of_Release) {
        String sql = "INSERT INTO MOVIES(Name,Actor,Actress,Director,Year_Of_Release) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setString(1, Name);
            pstmt.setString(2, Actor);
             pstmt.setString(3, Actress);
            pstmt.setString(4, Director);
          pstmt.setInt(4, Year_Of_Release);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        InsertApp mv = new InsertApp();
        // insert three new rows
        mv.insert("Malik","Fahad Fassil","Nimisha Sajayan","Mahesh Narayanan",2021);
     mv.insert("Bro Daddy","Mohanlal","Meena","Privithviraj",2022);
   mv.insert("CID Moosa","Dileep","Bhavana","Vidyasagar",2003);
  mv.insert("Hridhayam","Pranav Mohanlal","Darshana","Vineeth Srinivasan",2022);
  mv.insert("Drishyam","Mohanlal","Meena","Jeethu Joseph",2013);
    }

}