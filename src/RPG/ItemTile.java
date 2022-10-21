package RPG;

public class ItemTile extends Tile {
    
    protected Item item;
    
    //ItemTile constructor
    public ItemTile() {
        super();
        this.eventTriggers = rand.nextInt(10) <= 6;
        this.item = generateItem();
    }
    
    //Return the item's name
    public Item getItem() {
        return item;
    }

    //When the player steps on this tile
    @Override
    public void doEvent(Player player) {
        System.out.println("You see something glint on the ground...");
        
        //70% chance to find an item
        if (getEventTriggers()) {
            System.out.println("You found a " + getItem().getName() + "!");
            
            player.addItemToPlayer(this.getItem());
        }
        else
            System.out.println("It turned out to be nothing interesting.");
        
    }
    
    //Return a random item
    private Item generateItem() {
        int randItem = this.getRand().nextInt(2);
        Item item;
        
        //Equal chance of getting any type of item
        if (randItem == 0)
            item = new HealingPotion();
        else
            item = new DamagePotion();
                    
        return item;
    }
}