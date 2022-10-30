package RPG;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBManagerTest {
    DBManager dbManager;
    
    public DBManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dbManager = DBManager.getDBManagerInstance();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDBManagerInstance method, of class DBManager.
     */
    @Test
    public void testGetDBManagerInstance() {
        //Check if two instances of DBManager are the same Object
        DBManager expResult = dbManager;
        
        DBManager result = DBManager.getDBManagerInstance();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of establishConnection method, of class DBManager.
     */
    @Test
    public void testEstablishConnection() {
        dbManager.establishConnection();
        
        assertNotNull(dbManager.getConnection());
    }

    /**
     * Test of closeConnections method, of class DBManager.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCloseConnection() throws SQLException {
        dbManager.establishConnection();
        dbManager.closeConnection();
        
        assertTrue(dbManager.getConnection().isClosed());
    }
    
    /**
     * Test of updateDB method, of class DBManager.
     */
    @Test
    public void testUpdateDB() {
        String sql = "CREATE TABLE TEST(chars VARCHAR(10))";

        dbManager.establishConnection();
        boolean updateSuccess = dbManager.updateDB(sql);
        
        assertTrue(updateSuccess);
    }
    
    /**
     * Test of queryDB method, of class DBManager.
     * @throws java.sql.SQLException
     */
    @Test
    public void testQueryDB() throws SQLException {
        String sql = "INSERT INTO TEST (chars) VALUES ('test')";

        dbManager.establishConnection();
        dbManager.updateDB(sql);
        
        ResultSet rs = dbManager.queryDB("SELECT chars FROM TEST");

        assertTrue(rs.next());
    }
}
