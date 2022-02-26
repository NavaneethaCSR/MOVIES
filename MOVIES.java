

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

//  CONNECT TO DATABASE
public class MOVIES{
    Connection conn = null;
    public Connection connect() {
        
        try {
            // db parameters
            String url = "jdbc:sqlite/db/MOVIES.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } 
            catch (SQLException e) {
            System.out.println(e.getMessage());
        }

          return conn;
    }

//CREATE NEW TABLE
   public void createNewTable() {
     
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS MOVIES(\n"
	+"  Name TEXT,\n"
	+"  Actor TEXT,\n"
	+"  Actress TEXT,\n"
	+"  Director TEXT,\n"
	+"  Year_Of_Release INTEGER \n"
+  ");";

           try{

                Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
        } 
           catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


// INSERT INTO TABLE
    public void insert(String Name,String Actor,String Actress,String Director,int Year_Of_Release) {
        String sql = "INSERT INTO Movies(Name,Actor,Actress,Director,Year_Of_Release) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Name);
            pstmt.setString(2, Actor);
             pstmt.setString(3, Actress);
            pstmt.setString(4, Director);
          pstmt.setInt(4, Year_Of_Release);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   // SELECT ALL ROWS FROM THE TABLE

    public void selectAll(){
        String sql = "SELECT Name,Actor,Actress,Director,Year_Of_Release FROM Movies";
        
        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("Name") + " \t "+
                                   rs.getString("Actor") + " \t " +
                                   rs.getString("Actress") + " \t " +
                                   rs.getString("Director") + " \t " +
                                     rs.getInt("Year_Of_Release"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



public void getmovies(String Actor){
               String sql = "SELECT Name"
                          + "FROM Movies WHERE Actor=+Actor";
        
     try(
    Statement stmt= conn.createStatement();
       ResultSet rs = stmt.executeQuery(sql)){
            
     
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("Name"));
                                 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
       MOVIES mv= new MOVIES();
        mv.connect();
      mv.createNewTable();
     mv.insert("Malik","Fahad Fassil","Nimisha Sajayan","Mahesh Narayanan",2021);
     mv.insert("Bro Daddy","Mohanlal","Meena","Privithviraj",2022);
   mv.insert("CID Moosa","Dileep","Bhavana","Vidyasagar",2003);
  mv.insert("Hridhayam","Pranav Mohanlal","Darshana","Vineeth Srinivasan",2022);
  mv.insert("Drishyam","Mohanlal","Meena","Jeethu Joseph",2013);
 mv.selectAll();
mv.getmovies("Mohanlal");
    }
}

