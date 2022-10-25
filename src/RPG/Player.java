package RPG;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
    
    //Update the player's health, limiting to be between 0 & 20, inclusive
    public void updateHealth(int amount) {
        this.health += amount;
        
        if (this.health > 20)
            this.health = 20;
        else if (this.health < 0)
            this.health = 0;
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
    
    //Retrieve an item from the player (shows menu of all items + cancel + quit)
    public Item getPlayerItem() { 
        
        int selection, count;
        
        while (true) {
            System.out.println("         Items");

            count = 1;
            for (Item i : this.items) {
                System.out.println("   " + count + ". " + i.getName() + " [" + i.numHeld + " avail.]");
                count++;
            }

        }
        
        //Return the selected item
        return getItemAtIndex(selection - 1);
    }
    
    //Returns the item at an index of the player's items
    private Item getItemAtIndex(int index) {
        int count = 0;
        for (Item i : this.items) {
            if (count == index) return i;
            count++;
        }
        
        return null;
    }
    
    //Display a menu of actions for an item, returning the menu selection
    public int doItemMenu(Item item) {
        Scanner scan = new Scanner(System.in);
        int selection = 0;
        
        System.out.println();
        
        while (true) {
            System.out.println("=== " + item.getName() + " ===");
            System.out.println("      Item Actions");
            System.out.println("  1. Use item");
            System.out.println("  2. Throw item");
            System.out.println("  3. Cancel");
            System.out.println("  4. Quit");
            System.out.println("=======================");
            System.out.print("Enter your selection: ");

            try {
                selection = scan.nextInt();
                if (selection > 0 && selection <= 4)
                    break;
                else
                    System.out.println("\nInvalid input.\nPlease enter a number from the list of item actions.\n");
            }
            catch (InputMismatchException e) {
                System.out.println("\nInvalid input.\nPlease enter a number from the list of item actions.\n");
            }

            scan.nextLine();
        }
        
        System.out.println();
        
        return selection;
    }
    
    //Use an item
    public void useItem(Item item, int choice) {
        if (choice == 1) {
            item.useItem(this);
            removeItemFromPlayer(item);
        } else {
            item.throwItem();
            removeItemFromPlayer(item);
        }
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