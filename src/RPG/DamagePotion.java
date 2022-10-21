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
        int damage = this.rand.nextInt(10);
        
        System.out.println("It tastes horrible!");
        
        player.updateHealth(0 - damage);
    }

    //When the player throws the item
    @Override
    public void throwItem() {
        System.out.println("The potion makes a nasty");
        System.out.println("sizzling sound as it shatters,");
        System.out.println("but nothing happens.");
    }
    
    //When the player throws the item at the monster
    @Override
    public void throwItem(Monster monster) {
        int damage = this.rand.nextInt(5) + 5;
        
        System.out.println("The potion shatters");
        System.out.println("over the " + monster.getName() + ",");
        System.out.println("dealing " + damage + " damage!");

        monster.updateHealth(0 - damage);
    }
}