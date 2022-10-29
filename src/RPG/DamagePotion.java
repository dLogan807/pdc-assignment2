package RPG;

public class DamagePotion extends Potion {
    
    //Constructor for if none are held
    public DamagePotion() {
        super();
        this.name = "Damage Potion";
    }
    
    //Constructor if some are already held by the player (i.e. when loading a save)
    public DamagePotion(int numHeld) {
        super();
        this.numHeld = numHeld;
        this.name = "Damage Potion";
    }

    //Deal damage to the player on using the potion
    @Override
    public void useItem(Player player) {
        int damage = rand.nextInt(5) + 5;
        
        itemText = "It tastes horrible!\n\n";
        itemText += "You take " + damage + " damage!";
        
        player.updateHealth(0 - damage);
        
        if (player.isDead())
            itemText += "\n\nYou died...";
    }

    //When the player throws the item
    @Override
    public void throwItem() {
        itemText = "The potion makes a nasty sizzling sound as it shatters, but nothing happens.";
    }
    
    //When the player throws the item at the monster
    @Override
    public void throwItem(Monster monster) {
        int damage = rand.nextInt(5) + 5;
        
        itemText = "The potion shatters over the " + monster.getName() + ", dealing " + damage + " damage!";

        monster.updateHealth(0 - damage);
    }
}