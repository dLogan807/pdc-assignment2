package RPG;

import java.util.Random;

public final class Battle {
    private final Random rand;
    private final Player player;
    private final Monster monster;
    
    //Constructor for a battle
    public Battle(Player player, Monster monster) {
        this.rand = new Random();
        this.player = player;
        this.monster = monster;
    }
    
    //Return the player
    public Player getPlayer() {
        return player;
    }
    
    //Return the monster
    public Monster getMonster() {
        return monster;
    }
}