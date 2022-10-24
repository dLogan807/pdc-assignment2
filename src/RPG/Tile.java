package RPG;

import java.util.Random;

public class Tile {
    protected Random rand;
    protected boolean eventTriggers;
    
    //Tile constructor
    public Tile() {
        this.rand = new Random();
        this.eventTriggers = false;
    }

    //Return the Random Object
    public Random getRand() {
        return rand;
    }

    //Return whether the tile event triggers
    public boolean getEventTriggers() {
        return eventTriggers;
    }

    //Set whether the tile event triggers
    public void setEventTriggers(boolean eventTriggers) {
        this.eventTriggers = eventTriggers;
    }
    
    //Do the tile's event
    public void doEvent(Player player) {
        System.out.println("You continue down the corridor.");
    }
}