package RPG;

import java.util.Observable;

public final class Game extends Observable {
    private final Player player;
    private Battle battle;
    private BattleView battleView;
    private BattleController battleController;
    
    //Construct a game
    public Game(Player player) {
        this.player = player;
    }
    
    //Return the player
    public Player getPlayer() {
        return this.player;
    }
    
    //Returns the battle model
    public Battle getBattle() {
        return battle;
    }
    
    //Returns the battle's view
    public BattleView getBattleView() {
        return battleView;
    }
    
    //Returns the battle's controller
    public BattleController getBattleController() {
        return battleController;
    }
    
    //Sets the battle model
    public void setBattle(Battle battle) {
        this.battle = battle;
    }
    
    //Sets the battle's view
    public void setBattleView(BattleView battleView) {
        this.battleView = battleView;
    }
    
    //Sets the battle's controller
    public void setBattleController(BattleController battleController) {
        this.battleController = battleController;
    }
    
}