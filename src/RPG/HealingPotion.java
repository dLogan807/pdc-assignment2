package RPG;

public class HealingPotion extends Potion {
    //Constructor for if none are held
    public HealingPotion() {
        super();
        this.name = "Healing Potion";
    }
    
    //Constructor if some are already held by the player (i.e. when loading a save)
    public HealingPotion(int numHeld) {
        super();
        this.numHeld = numHeld;
        this.name = "Healing Potion";
    }

    //Heal the player on using the potion
    @Override
    public void useItem(Player player) {
        int health = this.rand.nextInt(5) + 5;
        
        this.itemText = "It tastes sweet!\n\n";
        this.itemText += "You gain " + health + " health!";
        
        player.updateHealth(health);
    }

    //Display description of throwing the item
    @Override
    public void throwItem() {
        this.itemText = "The potion shatters, but nothing happens.";
    }

    //When the player throws the item at the monster
    @Override
    public void throwItem(Monster monster) {
        int damage = this.rand.nextInt(5);
        
        this.itemText = "The potion shatters over the monster!\n"
                        + "It seems mildy annoyed by it.\n\n";
        
        this.itemText += "It takes " + damage + " damage!";
        
        monster.updateHealth(0 - damage);
    }
}