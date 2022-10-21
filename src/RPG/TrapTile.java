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
        System.out.println("A pressure plate clicks!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {}
        
        //80% chance for a trap to trigger and deal damage to the player
        if (!getEventTriggers()) {
            boolean dodged = this.getRand().nextBoolean();
            if (dodged)
                System.out.println("You manage to evade the trap!");
            else
                System.out.println("Nothing seemed to happen.");
        } else {
            int damage = this.getRand().nextInt(3) + 1;
            player.updateHealth(0 - damage);
        }
    }    
}