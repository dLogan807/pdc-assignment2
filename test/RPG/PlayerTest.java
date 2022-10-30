package RPG;

import java.awt.Color;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

//Class for testing the Player class
public class PlayerTest {
    Player newPlayer, loadedPlayer;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //Create a new player
        newPlayer = new Player("New Player");
        
        //Items to add to the loaded player
        HashSet<Item> items = new HashSet();
        items.add(new HealingPotion(2));
        items.add(new DamagePotion());
        
        //Create an existing player with an id of 11, 9 health, 15 moveCount, 4 monstersFought, and some items
        loadedPlayer = new Player(11, "Loaded Player", 9, 15, 4, items);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updateMoveCount method, of class Player.
     */
    @Test
    public void testUpdateMoveCount() {
        int moveCount = 10;
        
        //Increase the player's movement count by 10
        for (int i = 0; i < moveCount; i++)
            newPlayer.updateMoveCount();
        
        assertEquals(moveCount, newPlayer.getMoveCount());
    }

    /**
     * Test of updateMonstersFought method, of class Player.
     */
    @Test
    public void testUpdateMonstersFought() {
        int numMonsters = 5;
        
        //Increase the number of monsters the player has fought by 5
        for (int i = 0; i < numMonsters; i++)
            newPlayer.updateMonstersFought();
        
        assertEquals(numMonsters, newPlayer.getMonstersFought());
    }

    /**
     * Test of calcScore method, of class Player.
     */
    @Test
    public void testCalcScore() {
        int expResult = 55;
        int result = loadedPlayer.calcScore();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getCroppedName method, of class Player.
     */
    @Test
    public void testGetCroppedNameShortName() {
        String expResult = "New Player";
        String result = newPlayer.getCroppedName();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCroppedName method, of class Player.
     */
    @Test
    public void testGetCroppedNameLongName() {
        String expResult = "Loaded Pla";
        String result = loadedPlayer.getCroppedName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getHealthColour method, of class Player.
     */
    @Test
    public void testGetHealthColourGoodHealth() {
        Color expResult = Color.WHITE;
        Color result = newPlayer.getHealthColour();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHealthColour method, of class Player.
     */
    @Test
    public void testGetHealthColourMedicoreHealth() {
        Color expResult = Color.ORANGE;
        
        newPlayer.updateHealth(-11);
        Color result = newPlayer.getHealthColour();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHealthColour method, of class Player.
     */
    @Test
    public void testGetHealthColourBadHealth() {
        Color expResult = Color.RED;
        
        newPlayer.updateHealth(-19);
        Color result = newPlayer.getHealthColour();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of updateHealth method, of class Player.
     */
    @Test
    public void testUpdateHealthOverMax() {      
        int amount = 20;

        newPlayer.updateHealth(amount);

        assertEquals(amount, newPlayer.getHealth());
    }
    
    /**
     * Test of updateHealth method, of class Player.
     */
    @Test
    public void testUpdateHealthUnderMin() {      
        int amount = -30;

        newPlayer.updateHealth(amount);

        assertEquals(0, newPlayer.getHealth());
    }
    

    /**
     * Test of hasItems method, of class Player.
     */
    @Test
    public void testHasItems() {

        boolean result = loadedPlayer.hasItems();
        assertTrue(result);
    }
    
    /**
     * Test of hasItems method, of class Player.
     */
    @Test
    public void testHasNoItems() {

        boolean result = newPlayer.hasItems();
        assertFalse(result);
    }

    /**
     * Test of addItemToPlayer method, of class Player.
     */
    @Test
    public void testAddItemToPlayer() {
        Item item = new HealingPotion();

        newPlayer.addItemToPlayer(item);
        
        assertTrue(newPlayer.hasItems());
    }

    /**
     * Test of isLoaded method, of class Player.
     */
    @Test
    public void testIsLoaded() {
        boolean result = loadedPlayer.isLoaded();
        
        assertTrue(result);
    }
    /**
     * Test of isLoaded method, of class Player.
     */
    @Test
    public void testIsNotLoaded() {
        boolean result = newPlayer.isLoaded();
        
        assertFalse(result);
    }
    

    /**
     * Test of isDead method, of class Player.
     */
    @Test
    public void testIsDead() {
        newPlayer.updateHealth(-20);
        boolean result = newPlayer.isDead();
        
        assertTrue(result);
    }
    
    /**
     * Test of isDead method, of class Player.
     */
    @Test
    public void testIsNotDead() {
        boolean result = newPlayer.isDead();
        
        assertFalse(result);
    }

    /**
     * Test of removeItemFromPlayer method, of class Player.
     */
    @Test
    public void testRemoveItemFromPlayer() {
        Item item = new DamagePotion();

        boolean removed = loadedPlayer.removeItemFromPlayer(item);
        
        assertTrue(removed);
    }
    
    /**
     * Test of removeItemFromPlayer method, of class Player.
     */
    @Test
    public void testRemoveItemFromPlayerNoItems() {
        Item item = new DamagePotion();

        boolean removed = newPlayer.removeItemFromPlayer(item);
        
        assertFalse(removed);
    }

    /**
     * Test of getItemFromName method, of class Player.
     */
    @Test
    public void testGetItemFromName() {
        String itemName = "Healing Potion";
        Item result = loadedPlayer.getItemFromName(itemName);
        
        Item expResult = new HealingPotion();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getItemFromName method, of class Player.
     */
    @Test
    public void testGetItemFromNameNoItems() {
        String itemName = "Healing Potion";
        Item result = newPlayer.getItemFromName(itemName);
        
        assertNull(result);
    }
    
    /**
     * Test of useItem method, of class Player.
     */
    @Test
    public void testUseItemEffectOnPlayer() {
        Item item = new DamagePotion();
        int originalHealth = newPlayer.getHealth();

        newPlayer.useItem(item);
        
        assertNotEquals(originalHealth, newPlayer.getHealth());
    }
    
    /**
     * Test of useItem method, of class Player.
     */
    @Test
    public void testUseItemRemovesItem() {
        Item item = new DamagePotion();

        loadedPlayer.useItem(item);
        
        assertFalse(loadedPlayer.removeItemFromPlayer(item));
    }
    
    /**
     * Test of useItem method, of class Player.
     */
    @Test
    public void testUseItemDecreasesNumHeld() {
        Item item = new HealingPotion();

        loadedPlayer.useItem(item);
        
        assertEquals(1, loadedPlayer.getItemFromName("Healing Potion").numHeld);
    }

    //NOTE: Unnecessary to test throwItem method as useItem completes the same operations

    /**
     * Test of throwItem method, of class Player.
     */
    @Test
    public void testThrowItem_Item_Monster() {
        Monster monster = new Bat();
        monster.updateHealth(-9); //Bat has 10 health, any more damage will kill it

        loadedPlayer.throwItem(loadedPlayer.getItemFromName("Damage Potion"), monster);

        assertTrue(monster.isDead());
    }

    /**
     * Test of attack method, of class Player.
     */
    @Test
    public void testAttack() {
        Monster monster = new Spider();
        monster.updateHealth(-19); //Spider has 20 health, any more damage will kill it
        
        newPlayer.attack(monster);
        
        assertTrue(monster.isDead());
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        String expResult = "11 Loaded Player";
        
        String result = loadedPlayer.toString();
        
        assertEquals(expResult, result);
    }
    
}
