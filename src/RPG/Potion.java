package RPG;

public abstract class Potion extends Item {
    
    //Potion constructor
    public Potion() {
        super();
    }
    
    //Abstact method for throwing the potion at a monster
    public abstract void throwItem(Monster m);
}