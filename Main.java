package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sqlitetutorial.net
 */
public class Main {

    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/MOVIES.db";
        
        // SQL statement for creating a new table
               String sql = "CREATE TABLE IF NOT EXISTS MOVIES(\n"
	+"  Name TEXT,\n"
	+"  Actor TEXT,\n"
	+"  Actress TEXT,\n"
	+"  Director TEXT,\n"
	+"  Year_Of_Release INTEGER \n"
+  ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();
    }

}
