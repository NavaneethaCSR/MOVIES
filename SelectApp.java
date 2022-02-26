package net.sqlitetutorial;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 *
 * @author sqlitetutorial.net
 */
public class SelectApp {

    /**
     * Connect to the test.db database
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
     * select all rows in the MOVIES table
     */
    public void selectAll(){
        String sql = "SELECT Name,Actor,Actress,Director,Year_Of_Release FROM Movies";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
              while (rs.next()) {
                System.out.println(
                              rs.getString("Name") + " \t "+
                                   rs.getString("Actor") + " \t " +
                                   rs.getString("Actress") + " \t " +
                                   rs.getString("Director") + " \t " +
                                     rs.getInt("Year_Of_Release"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
   
    /**
     * @param args the command line arguments
     */
    /**
     * Get the warehouse whose capacity greater than a specified capacity
     * @param capacity 
     */
    public void select(String Actor){
               String sql = "SELECT Name  "
                          + "FROM MOVIES WHERE Actor= +Actor";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,"Name");
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
              while (rs.next()) {
                System.out.println(rs.getString("Name"));
                                 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }







    public static void main(String[] args) {
        SelectApp app = new SelectApp();
        app.selectAll();
app.select("Mohanlal");


    }

}