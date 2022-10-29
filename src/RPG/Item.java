package RPG;

import java.util.Random;

public abstract class Item {
    protected String name;
    protected int numHeld;
    protected Random rand;
    protected String itemText;
    
    //Item constructor
    public Item() {
        this.numHeld = 1;
        this.rand = new Random();
    }
    
    //Return the item's name
    public String getName() {
        return name;
    }    
    
    //Gets the number of the item held by the player
    public int getNumHeld() {
        return this.numHeld;
    }
    
    //Gets how the item's text
    public String getItemText() {
        return this.itemText;
    }
    
    //Abstract methods for the default operations of using and throwing the item
    public abstract void useItem(Player player);
    public abstract void throwItem();
    
    //Check if two items are equal by comparing their names
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Item))
            return false;
        Item other = (Item) o;
        
        return (this.getName().equals(other.getName()));
    }
    
    //Create hashCode from the item's name
    @Override
    public int hashCode() {
        int hashCode = 1;
                
        for (int i = 0; i < this.getName().length(); i++)
            hashCode += this.getName().charAt(i);
        hashCode = 183 * hashCode;
        
        return hashCode;
    }
    
    //Print the item and the number held
    @Override 
    public String toString() {
        return this.getName() + " " + this.numHeld;
    }
}