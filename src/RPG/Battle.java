package RPG;

public final class Battle {
    private final Player player;
    private final Monster monster;
    
    //Constructor for a battle
    public Battle(Player player, Monster monster) {
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