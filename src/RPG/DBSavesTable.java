package RPG;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

public final class DBSavesTable extends DBTable {
    
    public DBSavesTable() {
        super();
        this.tableName = "SAVES";
        this.setupTable();
    }
    
    //Create the SAVES table if it doesn't already exist
    @Override
    protected void setupTable() {
        if (!tableExists(this.tableName))
            createTable();
    }
    
    //Create the saves table
    @Override
    protected void createTable()  {
        try {
            this.statement = conn.createStatement();
            this.statement.executeUpdate("CREATE TABLE " + this.tableName + "("
                                        + "save_id INT PRIMARY KEY,"
                                        + "name VARCHAR(200), "
                                        + "health INT,"
                                        + "move_count INT,"
                                        + "monsters_fought INT,"
                                        + "items VARCHAR(41))");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Return all saves in the table as a HashMap, mapped to their id
    @Override
    protected HashMap getTableData() {
        ResultSet rs = null;
        HashMap<Integer, Player> saves = new HashMap();

        try {
            rs = this.statement.executeQuery("SELECT save_id FROM " + this.tableName);
            
            while (rs.next()) {
                saves.put(rs.getInt(1), loadSave(rs.getInt(1)));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return saves;
    }
    
    //Load a save from the database into a Player object, returning its
    public Player loadSave(int saveID) {
        ResultSet rs = null;
        
        try {
            rs = this.statement.executeQuery("SELECT save_id, name, health, monsters_fought, move_count, items"
                    + " FROM " + this.tableName
                    + " WHERE save_id = " + saveID);
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultSetToPlayer(rs);
    }
    
    //Convert a ResultSet into a Player object
    private Player resultSetToPlayer(ResultSet rs) {
        Player player = null;
        
        try {
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
        try {
            this.statement.executeUpdate("DELETE FROM " + this.tableName
                                        + " WHERE save_id = " + saveID);
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    //Save the player's data by updating it if a save exists or creating a new save
    public void save(Player player) {
        if (player.getID() == -1) {
            this.addSave(player);
        } else
            this.updateSave(player);
    }
    
    //Update the player's existing save in the database
    public void updateSave(Player player) {
        try {
            this.statement.executeUpdate("UPDATE " + this.tableName
                                        + " SET health = " + player.getHealth() 
                                        + ", move_count = " + player.getMoveCount() 
                                        + ", monsters_fought = " + player.getMonstersFought()
                                        + ", items = " + player.getItems().toString()
                                        + " WHERE saveID = " + player.getID());
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    //Insert the player's save into a new entry in the table
    public void addSave(Player player) {
        try {
            this.statement.executeUpdate("INSERT INTO " + this.tableName
                                        + " VALUES ("
                                        + player.getName() + ","
                                        + player.getHealth() + ","
                                        + player.getMoveCount() + ","
                                        + player.getMonstersFought() + ","
                                        + player.getItems().toString() + ")");
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
}