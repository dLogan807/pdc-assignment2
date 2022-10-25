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
        this.eventText = "You see something glint on the ground...\n\n";
        
        //70% chance to find an item
        if (getEventTriggers()) {
            this.eventText += "You found a " + getItem().getName() + "!";
            
            player.addItemToPlayer(this.getItem());
        }
        else
            this.eventText += "It turned out to be nothing interesting.";
        
    }
    
    //Return a random item
    private Item generateItem() {
        int randItem = this.getRand().nextInt(2);
        Item item;
        
        //Equal chance of getting any type of item
        if (randItem == 0)
            item = ItemFactory.getItem("healing potion");
        else
            item = ItemFactory.getItem("damage potion");
                    
        return item;
    }
}