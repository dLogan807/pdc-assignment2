package RPG;

public class TrapTile extends Tile {
    
    //TrapTile Constructor
    public TrapTile() {
        super();
        //80% chance for event to trigger
        this.eventTriggers = rand.nextInt(10) <= 7;
    }

    //Do the tile's event - probably set off a trap
    @Override
    public void doEvent(Player player) {
        this.eventText = "A pressure plate clicks!\n\n";
        
        //80% chance for a trap to trigger and deal damage to the player
        if (!getEventTriggers()) {
            boolean dodged = this.getRand().nextBoolean();
            if (dodged)
                this.eventText += "You manage to evade the trap!";
            else
                this.eventText += "Nothing seemed to happen.";
        } else {
            int damage = this.getRand().nextInt(3) + 1;
            player.updateHealth(0 - damage);
        }
    }    
}