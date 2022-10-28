package RPG;

public class ChestTile extends ItemTile {
    
    //Constructor
    public ChestTile() {
        super(); //Will generate a random item
    }

    //Add a randomly generated item to the player
    @Override
    public void doEvent(Player player) {
        this.eventText = "You find a large chest.\n\nOpening it, you find a "
                          + this.getItem().getName() + "!";
            
        player.addItemToPlayer(this.getItem());
    }
}