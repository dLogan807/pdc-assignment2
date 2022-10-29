package RPG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Note that this class follows the Singleton design pattern
public final class DBManager {
    private static DBManager instance;
    private final String URL = "jdbc:derby:RPGDB;create=true";
    private Connection conn;

    //Private DBManager constructor
    private DBManager() {
        //Fix primary key values incrementing by 100
        System.setProperty("derby.language.sequence.preallocator", String.valueOf(1));
        
        establishConnection();
    }
    
    //Return the singleton instance of the DBManager, creating it if it doesn't exist yet
    public static DBManager getDBManagerInstance() {
        if (instance == null)
            instance = new DBManager();
        
        return instance;
    }
    
    //Prevent the Database Manager singleton object instance from being cloned
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    //Return the database connection
    public Connection getConnection() {
        return conn;
    }

    //Establish a connection to the database
    public void establishConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL);
                System.out.println(URL + "\n- Connection successfully established -");
            } catch (SQLException ex) {
                System.out.println("Cannot access the embedded database from multiple programs simultaneously!");
            }
        }
        
        verifyConnection();
    }
    
    //Quit the program if a connection can't be established
    public void verifyConnection() {
        if (conn == null) {
            System.out.println("Connected failed, exiting...");
            System.exit(-1);
        }
    }

    //Close the connection to the database
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //Query the database on the query and return a ResultSet of the results
    public ResultSet queryDB(String sql) {
        Connection connection = conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultSet;
    }

    //Execute the SQL string on the database
    public void updateDB(String sql) {
        Connection connection = conn;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}