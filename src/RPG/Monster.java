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
    
    //Return the monster's text
    public String getMonsterText() {
        return monsterText;
    }
    
    //Update the monster's health from the amount
    public void updateHealth(int amount) {
        health += amount;
    }
    
    //Returns true if the monster is dead
    public boolean isDead() {
        return health <= 0;
    }
    
    //Abstract methods
    public abstract void attack(Player player);
    public abstract void doNothing();
}