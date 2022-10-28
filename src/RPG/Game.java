package RPG;

import java.util.Observable;

public final class Game extends Observable {
    private final Player player;
    private Battle battle;
    private boolean loaded;
    
    //Construct a game
    public Game(Player player, boolean loaded) {
        this.player = player;
        this.loaded = loaded;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    //Get whether the game was loaded
    public boolean getLoaded() {
        return this.loaded;
    }
    
    //Sets the battle object
    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}