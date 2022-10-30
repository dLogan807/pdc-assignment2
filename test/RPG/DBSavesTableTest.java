package RPG;

import java.util.HashMap;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBSavesTableTest {
    DBSavesTable savesTable;
    
    public DBSavesTableTest() {
        this.savesTable = new DBSavesTable();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        if (savesTable.tableExists("SAVES"))
            savesTable.dbManager.updateDB("DROP TABLE SAVES");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createTable method, of class DBSavesTable.
     */
    @Test
    public void testCreateTable() {
        boolean success = savesTable.createTable();
                
        assertTrue(success);
    }

    /**
     * Test of getTableData method, of class DBSavesTable.
     */
    @Test
    public void testGetTableData() {
        boolean isEmpty;
                
        savesTable.createTable();
        savesTable.addSave(new Player("test"));
        
        HashMap data = savesTable.getTableData();
        
        isEmpty = data.isEmpty();
        
        assertFalse(isEmpty);
    }
    
    /**
     * Test of getTableData method, of class DBSavesTable.
     */
    @Test
    public void testGetTableDataTableEmpty() {
        boolean result;
        
        savesTable.createTable();
        
        HashMap data = savesTable.getTableData();
        
        result = data.isEmpty();
        
        assertTrue(result);
    }

    /**
     * Test of loadSave method, of class DBSavesTable.
     */
    @Test
    public void testLoadSave() {
        int saveID = 1;
     
        savesTable.createTable();
        savesTable.addSave(new Player("test player"));
        
        Player result = savesTable.loadSave(saveID);
        assertEquals(result.getName(), "test player");
    }

    /**
     * Test of deleteSave method, of class DBSavesTable.
     */
    @Test
    public void testDeleteSave() {
        testLoadSave(); //Generate data
        
        savesTable.deleteSave(1);
        
        boolean result = savesTable.saveExists(1);
        
        assertFalse(result);
    }

    /**
     * Test of updateSave method, of class DBSavesTable.
     */
    @Test
    public void testUpdateSave() {
        testLoadSave(); //Generate data
        
        int oldMoveCount = savesTable.loadSave(1).getMoveCount();

        HashSet<Item> items = new HashSet();
        savesTable.updateSave(new Player(1, "test player", 13, 23, 1, items));
        
        int newMoveCount = savesTable.loadSave(1).getMoveCount();
        
        assertNotEquals(oldMoveCount, newMoveCount);
    }

    /**
     * Test of saveExists method, of class DBSavesTable.
     */
    @Test
    public void testSaveExistsDoesNotExist() {
        boolean expectedResult;
        
        savesTable.createTable();
        
        expectedResult = savesTable.saveExists(100);
        
        assertFalse(expectedResult);
    }
}