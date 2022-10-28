package RPG;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;

public final class Player {
    private int id;
    private final String name;
    private int health, moveCount, monstersFought;
    private HashSet<Item> items; 
    private boolean quitFlag; //If set, will quit the program
    private String playerText;
    
    //Constructor for creating a new player
    public Player(String name) {
        this.id = -1;
        this.name = name;
        this.health = 20;
        this.moveCount = 0;
        this.monstersFought = 0;
        this.items = new HashSet();
        this.quitFlag = false;
        
        
    }
    
    //Constructor for loading an existing player
    public Player(int id, String name, int health, int moveCount, int monstersFought, HashSet<Item> items) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.moveCount = moveCount;
        this.monstersFought = monstersFought;
        this.items = items;
        this.quitFlag = false;
        
        addItemToPlayer(new HealingPotion());
        addItemToPlayer(new DamagePotion());
    }
    
    //Get the player's ID
    public int getID() {
        return this.id;
    }

    //Get the player's name
    public String getName() {
        return this.name;
    }
    
    //Get the player's health
    public int getHealth() {
        return health;
    }
    
    //Get the player's movement count
    public int getMoveCount() {
        return this.moveCount;
    }
    
    //Increment the player's movement count
    public void updateMoveCount() {
        this.moveCount++;
    }
    
    //Get the number of monsters the player has fought
    public int getMonstersFought() {
        return this.monstersFought;
    }
    
    //Increment the number of monsters the player has fought
    public void updateMonstersFought() {
        this.monstersFought ++;
    }
    
    //Get the player's items
    public HashSet<Item> getItems() {
        return this.items;
    }
    
    //Get player's quit flag
    public boolean getQuitFlag() {
        return this.quitFlag;
    }
    
    //Set the player's quit flag
    public void setQuitFlag(boolean quitFlag) {
        this.quitFlag = quitFlag;
    }
    
    //Returns a cropped version of the player's name if it's too long
    public String getCroppedName() {
        String croppedName, fullName = this.getName();
        int length = fullName.length();
        
        if (fullName.length() > 10)
            length = 10;
        
        croppedName = fullName.substring(0, length);
        
        return croppedName;
    }
    
    //Get's what color the player's health should display as
    public Color getHealthColour() {
        Color healthColour;
        
        if (this.getHealth() < 5)
            healthColour = Color.RED;
        else if (this.getHealth() < 10)
            healthColour = Color.ORANGE;
        else
            healthColour = Color.WHITE;
        
        return healthColour;
    }
    
    //Update the player's health, limiting to be between 0 & 20, inclusive
    public void updateHealth(int amount) {
        this.health += amount;
        
        if (this.health > 20)
            this.health = 20;
        else if (this.health < 0)
            this.health = 0;
    }
    
    //Returns true if a player has items
    public boolean hasItems() {
        return !this.items.isEmpty();
    }
    
    //Add an item to the player's items
    public void addItemToPlayer(Item item) {
        //If the player already has the item, increment the number of those items they have
        if (this.items.contains(item)) {
            for (Item i : this.items) {
                if (i.equals(item))
                    i.numHeld++;
            }
        } else
            this.items.add(item);
    }
    
    //Remove an item from the player
    public void removeItemFromPlayer(Item item) {
        for (Item i : this.items) {
            if (i.equals(item)) {
                if (i.numHeld > 1)
                    i.numHeld--;
                else
                    this.items.remove(i);
                break;
            }
        }
    }
    
    //Returns the item matching the given name
    public Item getItemFromName(String itemName) {
        for (Item i : this.items) {
            if (i.getName().equalsIgnoreCase(itemName)) 
                return i;
        }
        
        return null;
    }
    
    //Use an item
    public void useItem(Item item) {
        item.useItem(this);
        removeItemFromPlayer(item);
    }
    
    //Throw an item
    public void throwItem(Item item) {
        item.throwItem();
        removeItemFromPlayer(item);
    }
    
    //Use an item against a monster
    public void useItem(Item item, Monster monster) {
        if (item instanceof Potion) {
            ((Potion) item).throwItem(monster);
        }
        removeItemFromPlayer(item);
    }
    
    //Attack a monster, dealing damage to it
    public void attack(Monster monster) {
        Random rand = new Random();
        int damage = rand.nextInt(10) + 1;
        
        System.out.println("You punch the " + monster.getName() + ",");
        System.out.println("dealing " + damage + " damage!");
        
        monster.updateHealth(0 - damage);
    }
    
    @Override
    public String toString() {
        return this.getID() + " " + this.getName();
    }
}