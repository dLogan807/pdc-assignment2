package RPG;

import java.util.HashMap;
import java.util.Observable;

public class Menu extends Observable {
    private Player player;
    private final DBSavesTable savesTable;
    private final DBScoresTable scoresTable;
    private Game game;
    private GameView gameView;
    private GameController gameController;
    
    //Menu constructor
    public Menu() {
        this.savesTable = new DBSavesTable();
        this.scoresTable = new DBScoresTable();
    }
    
    //Get the player
    public Player getPlayer() {
        return player;
    }
    
    //Return the game's model
    public Game getGame() {
        return game;
    }
        
    //Return the game's controller
    public GameController getGameController() {
        return gameController;
    }

    //Return the game's view
    public GameView getGameView() {
        return gameView;
    }
    
    //Set the game's model
    public void setGame(Game game) {
        this.game = game;
    }

    //Set the game's view
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
    
    //Set the game's controller
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
    
    //Set the player
    public void setPlayer(Player player) {
        this.player = player;
    } 
  
    //Add the player's score to the score database
    public void addScore() {
        scoresTable.addScore(new Score(player.getName(), calcScore()));
    }
    
    //Calculate the player's score
    private int calcScore() {
        return player.getMoveCount() + player.getMonstersFought() * 10;
    }
    
    //Return a String of player scores
    public String getScores() {
        HashMap<String, Score> scores = scoresTable.getTableData();
        String scoreString = "";
        
        if (!scores.isEmpty()) {
            for (Score s : scores.values())
                scoreString += s + "\n";
        }
        
        return scoreString;
    }
    
    //Get the best game score
    public String getBestScore() {
        String scoreDetails;
        
        try {
            scoreDetails = scoresTable.getBestScore().toString();
        }
        catch (NullPointerException ex) {
            scoreDetails = "No scores yet!";
        }
        
        return scoreDetails;
    }
    
    //Return a HashMap of all saves in the database
    public HashMap getSaves() {
        return savesTable.getTableData();
    }
    
    //Load the a player from the database using their id
    public void loadPlayer(int id) {
        player = savesTable.loadSave(id);
    }
    
    //Save a player to the database
    public void savePlayer() {
        savesTable.save(player);
    }
    
    //Delete a player by their save id if they have a save
    public void deletePlayerIfExists() {
        if (saveExists())
            savesTable.deleteSave(player.getID());
    }
    
    //Returns true if a save for the player exists
    private boolean saveExists() {
        return savesTable.saveExists(player.getID());
    }
}