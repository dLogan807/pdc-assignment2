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
        eventText = "A pressure plate clicks!\n\n";
        
        //80% chance for a trap to trigger and deal damage to the player
        if (getEventTriggers()) {
            boolean dodged = getRand().nextBoolean();
            if (dodged)
                eventText += "You manage to evade the trap!";
            else {
                int damage = getRand().nextInt(3) + 1;
                player.updateHealth(0 - damage);
                
                eventText += "You take " + damage + " damage!";
                
                if (player.isDead())
                    eventText += "\n\nYou died...";
            }    
        } else
            eventText += "Nothing seemed to happen.";
    }    
}