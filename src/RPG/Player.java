package RPG;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;

public final class Player {
    private int id;
    private final String name;
    private int health, moveCount, monstersFought;
    private HashSet<Item> items; 
    private String playerText;
    
    //Constructor for creating a new player
    public Player(String name) {
        this.id = -1;
        this.name = name;
        this.health = 20;
        this.moveCount = 0;
        this.monstersFought = 0;
        this.items = new HashSet();
    }
    
    //Constructor for loading an existing player
    public Player(int id, String name, int health, int moveCount, int monstersFought, HashSet<Item> items) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.moveCount = moveCount;
        this.monstersFought = monstersFought;
        this.items = items;
    }
    
    //Get the player's ID
    public int getID() {
        return id;
    }

    //Get the player's name
    public String getName() {
        return name;
    }
    
    //Get the player's health
    public int getHealth() {
        return health;
    }
    
    //Get the player's movement count
    public int getMoveCount() {
        return moveCount;
    }
    
    //Increment the player's movement count
    public void updateMoveCount() {
        moveCount++;
    }
    
    //Get the number of monsters the player has fought
    public int getMonstersFought() {
        return monstersFought;
    }
    
    //Increment the number of monsters the player has fought
    public void updateMonstersFought() {
        monstersFought ++;
    }
    
    //Get the player's items
    public HashSet<Item> getItems() {
        return items;
    }
    
    //Returns the player's text
    public String getPlayerText() {
        return playerText;
    }
    
    //Returns a cropped version of the player's name if it's too long
    public String getCroppedName() {
        String croppedName, fullName = getName();
        int length = fullName.length();
        
        if (fullName.length() > 10)
            length = 10;
        
        croppedName = fullName.substring(0, length);
        
        return croppedName;
    }
    
    //Get's what color the player's health should display as
    public Color getHealthColour() {
        Color healthColour;
        
        if (getHealth() < 5)
            healthColour = Color.RED;
        else if (getHealth() < 10)
            healthColour = Color.ORANGE;
        else
            healthColour = Color.WHITE;
        
        return healthColour;
    }
    
    //Update the player's health, limiting to be between 0 & 20, inclusive
    public void updateHealth(int amount) {
        health += amount;
        
        if (health > 20)
            health = 20;
        else if (health < 0)
            health = 0;
    }
    
    //Returns true if a player has items
    public boolean hasItems() {
        return !items.isEmpty();
    }
    
    //Add an item to the player's items
    public void addItemToPlayer(Item item) {
        //If the player already has the item, increment the number of those items they have
        if (items.contains(item)) {
            for (Item i : items) {
                if (i.equals(item))
                    i.numHeld++;
            }
        } else
            items.add(item);
    }
    
    //Returns true if the player loaded a game
    public boolean isLoaded() {
        return !(id == -1);
    }
    
    //Returns true if the player is dead (health == 0)
    public boolean isDead() {
        return health == 0;
    }
    
    //Remove an item from the player
    public void removeItemFromPlayer(Item item) {
        for (Item i : items) {
            if (i.equals(item)) {
                if (i.numHeld > 1)
                    i.numHeld--;
                else
                    items.remove(i);
                break;
            }
        }
    }
    
    //Returns the item matching the given name
    public Item getItemFromName(String itemName) {
        for (Item i : items) {
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
    public void throwItem(Item item, Monster monster) {
        if (item instanceof Potion)
            ((Potion) item).throwItem(monster);
        
        removeItemFromPlayer(item);
        
        if (monster.isDead())
            playerText += "You defeated the " + monster.getName() + "!";
    }
    
    //Attack a monster, dealing damage to it and returning the damage amount
    public int attack(Monster monster) {
        Random rand = new Random();
        int damage = rand.nextInt(10) + 1;
        
        playerText = "You punch the " + monster.getName() + ", dealing " + damage + " damage!";
        
        monster.updateHealth(0 - damage);
        
        if (monster.isDead())
            playerText += "\n\nYou defeated the " + monster.getName() + "!";
        
        return damage;
    }
    
    @Override
    public String toString() {
        return getID() + " " + getName();
    }
}