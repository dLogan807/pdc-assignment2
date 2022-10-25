package RPG;

import java.util.Random;

public abstract class Monster {
    Random rand;
    protected String name;
    protected int health;
    protected String monsterText;
    
    //Monster constructor
    public Monster() {
        this.rand = new Random();
    }
    
    //Return the monster's name
    public String getName() {
        return name;
    }

    //Return the monster's health
    public int getHealth() {
        return health;
    }
    
    //Update the monster's health from the amount
    public void updateHealth(int amount) {
        this.health += amount;
    }
    
    //Abstract methods
    public abstract void attack(Player player);
    public abstract void doNothing();
}