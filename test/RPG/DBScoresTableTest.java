package RPG;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBScoresTableTest {
    DBScoresTable scoresTable;
    
    public DBScoresTableTest() {
        this.scoresTable = new DBScoresTable();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        if (scoresTable.tableExists("SCORES"))
            scoresTable.dbManager.updateDB("DROP TABLE SCORES");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createTable method, of class DBScoresTable.
     */
    @Test
    public void testCreateTable() {
        boolean success = scoresTable.createTable();
                
        assertTrue(success);
    }

    /**
     * Test of getTableData method, of class DBScoresTable.
     */
    @Test
    public void testGetTableData() {
        boolean isEmpty;
        
        scoresTable.createTable();
        scoresTable.addScore(new Score("test", 10)); //Consequently tests addScore
        
        HashMap data = scoresTable.getTableData();
        
        isEmpty = data.isEmpty();
        
        assertFalse(isEmpty);
    }

    /**
     * Test of getBestScore method, of class DBScoresTable.
     */
    @Test
    public void testGetBestScore() {
        scoresTable.createTable();
        
        scoresTable.addScore(new Score("low score", 34));
        scoresTable.addScore(new Score("high score", 35));
        
        Score bestScore = scoresTable.getBestScore();
        
        assertEquals(35, bestScore.getScore());
    }
    
}
