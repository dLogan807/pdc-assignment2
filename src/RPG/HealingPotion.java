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
        
        System.out.println("It tastes sweet!");
        
        player.updateHealth(health);
    }

    //Display description of throwing the item
    @Override
    public void throwItem() {
        System.out.println("The potion shatters,");
        System.out.println("but nothing happens.");
    }

    //When the player throws the item at the monster
    @Override
    public void throwItem(Monster monster) {
        int damage = this.rand.nextInt(5);
        
        System.out.println("The potion shatters");
        System.out.println("over the monster!");
        System.out.println("It seems mildy");
        System.out.println("annoyed by it.");
        
        monster.updateHealth(0 - damage);
    }
}