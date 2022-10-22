package RPG;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public abstract class DBTable {

    protected final DBManager dbManager;
    protected Statement statement;
    protected String tableName;

    //Database Tables constructor
    public DBTable() {
        dbManager = DBManager.getDBManagerInstance();
    }
    
    //Check if a table exists in the database
    protected boolean tableExists(String table) {
        boolean exists = false;
        
        try {
            DatabaseMetaData dbmd = dbManager.getConnection().getMetaData();
            String[] types = {"TABLE"};
            statement = dbManager.getConnection().createStatement();
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
        dbManager.closeConnections();
    }
    
    //Abstract methods:
    
    //Create the table if not created yet
    protected abstract void setupTable();
    
    //Create the table
    protected abstract void createTable();
    
    //Return the contents of the table as a HashMap 
    protected abstract HashMap getTableData();
}