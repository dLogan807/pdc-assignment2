package RPG;

public class ChestTile extends ItemTile {
    
    //Constructor
    public ChestTile() {
        super(); //Will generate a random item
    }

    //Add a randomly generated item to the player
    @Override
    public void doEvent(Player player) {
        System.out.println("You find a large chest.");
        System.out.print("Opening it, you find a ");
        System.out.println(this.getItem().getName() + "!");
            
        player.addItemToPlayer(this.getItem());
    }
}