package RPG;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

public final class DBSavesTable extends DBTable {
    
    //DBSavesTable constructor
    public DBSavesTable() {
        super();
        this.tableName = "SAVES";
        
        //Create the table if it doesn't exist
        if (!tableExists(tableName))
            createTable();
    }
    
    //Create the saves table
    @Override
    protected boolean createTable()  {
        boolean created = dbManager.updateDB("CREATE TABLE " + tableName + "("
                            + "save_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                            + "name VARCHAR(200), "
                            + "health INT,"
                            + "move_count INT,"
                            + "monsters_fought INT,"
                            + "items VARCHAR(41))");
        
        return created;
    }
    
    //Return all saves in the table as a HashMap, mapped to their id
    @Override
    protected HashMap getTableData() {
        ResultSet rs = null;
        HashMap<Integer, Player> saves = new HashMap();

        try {
            rs = dbManager.queryDB("SELECT save_id FROM " + tableName);
            
            while (rs.next())
                saves.put(rs.getInt(1), loadSave(rs.getInt("save_id")));
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return saves;
    }
    
    //Load a save from the database into a Player object, returning its
    public Player loadSave(int saveID) {
        ResultSet rs = dbManager.queryDB("SELECT save_id, name, health, monsters_fought, move_count, items"
                                        + " FROM " + tableName
                                        + " WHERE save_id = " + saveID);
        
        return resultSetToPlayer(rs);
    }
    
    //Convert a ResultSet into a Player object
    private Player resultSetToPlayer(ResultSet rs) {
        Player player = null;
        
        try {
            rs.next();
            
            int saveID = rs.getInt("save_id");
            String name = rs.getString("name");
            int health = rs.getInt("health");
            int moveCount = rs.getInt("move_count");
            int monstersFought = rs.getInt("monsters_fought");
            String itemString = rs.getString("items");
            
            HashSet<Item> items = stringToItems(itemString);
            
            player = new Player(saveID, name, health, moveCount, monstersFought, items);
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return player;
    }
    
    //Convert a string of items (from the player save) to a HashSet of Items
    private HashSet<Item> stringToItems(String stringItems) {
        stringItems = stringItems.replace('[', ' ');
        stringItems = stringItems.replace(']', ' ');
        stringItems = stringItems.trim();
        String[] splitItems = stringItems.split(",");
        
        HashSet<Item> items = new HashSet();
        Item item;
        
        for (String s : splitItems) {
            if (getItemName(s).equals(new DamagePotion().getName())) {
                item = new DamagePotion(getNumberHeld(s));
                items.add(item);
            } else if (getItemName(s).equals(new HealingPotion().getName())) {
                item = new HealingPotion(getNumberHeld(s));
                items.add(item);
            }
        }
        
        return items;
    }
    
    //Return the number of items held from a string (remove characters)
    private int getNumberHeld(String item) {
        char[] charArray = item.toCharArray();
        String count = "";
  
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i]))
                count = count + charArray[i];
        }
        
        return Integer.parseInt(count);
    }
    
    //Get the name of an item from a string (remove integers)
    private String getItemName(String item) {
        char[] charArray = item.toCharArray();
        String name = "";
  
        for (int i = 0; i < charArray.length; i++) {
            if (!Character.isDigit(charArray[i]))
                name = name + charArray[i];
        }
        
        return name.trim();
    }
    
    public void deleteSave(int saveID) {
        dbManager.updateDB("DELETE FROM " + tableName
                        + " WHERE save_id = " + saveID);
    }
    
    //Save the player's data by updating it if a save exists or creating a new save
    public void save(Player player) {
        if (player.getID() == -1) {
            addSave(player);
        } else
            updateSave(player);
    }
    
    //Update the player's existing save in the database
    public void updateSave(Player player) {
        dbManager.updateDB("UPDATE " + tableName
                        + " SET health = " + player.getHealth() 
                        + ", move_count = " + player.getMoveCount() 
                        + ", monsters_fought = " + player.getMonstersFought()
                        + ", items = '" + player.getItems().toString() + "'"
                        + " WHERE save_id = " + player.getID());
    }
    
    //Insert the player's save into a new entry in the table
    public void addSave(Player player) {
        dbManager.updateDB("INSERT INTO " + tableName + " (name, health, move_count, monsters_fought, items)"
                        + " VALUES ("
                        + "'" + player.getName() + "'" + ", "
                        + player.getHealth() + ", "
                        + player.getMoveCount() + ", "
                        + player.getMonstersFought() + ", "
                        + "'" + player.getItems().toString() + "'" + ")");
    }
    
    //Returns true if a save with the id exists in the table
    public boolean saveExists(int id) {
        ResultSet rs = dbManager.queryDB("SELECT * FROM " + tableName
                        + " WHERE save_id = " + id);
        
        try {
            if (rs.next())
                return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
}