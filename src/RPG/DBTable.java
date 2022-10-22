package RPG;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public abstract class DBTable {

    private final DBManager dbManager;
    protected final Connection conn;
    protected Statement statement;
    protected String tableName;

    //Database Tables constructor
    public DBTable() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    //Check if a table exists in the database
    protected boolean tableExists(String table) {
        boolean exists = false;
        
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);
            
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (tableName.equalsIgnoreCase(table)) {
                    exists = true;
                    break;
                }
            }
            
            rs.close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return exists;
    }
    
    //Close the connection to the database
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Abstract methods:
    
    //Create the table if not created yet
    protected abstract void setupTable();
    
    //Create the table
    protected abstract void createTable();
    
    //Return the contents of the table as a HashMap 
    protected abstract HashMap getTableData();
}