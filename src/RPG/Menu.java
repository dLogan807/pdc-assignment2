package RPG;

import java.util.HashMap;
import java.util.Observable;

public class Menu extends Observable {
    private Player player;
    private DBSavesTable savesTable;
    private DBScoresTable scoresTable;
    
    //Get the player
    public Player getPlayer() {
        return this.player;
    }
    
    //Set the player
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Menu() {
        this.savesTable = new DBSavesTable();
        this.scoresTable = new DBScoresTable();
    }
    
    //Calculate the player's score
    private int calcScore() {
        return player.getMoveCount() + player.getMonstersFought() * 10;
    }
    
    //Return a String of player scores
    public String getScores() {
        HashMap<String, Score> scores = this.scoresTable.getTableData();
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
            scoreDetails = this.scoresTable.getBestScore().toString();
        }
        catch (NullPointerException ex) {
            scoreDetails = "No scores yet!";
        }
        
        return scoreDetails;
    }
    
    //Return a HashMap of all saves in the database
    public HashMap getSaves() {
        return this.savesTable.getTableData();
    }
    
    //Load the a player from the database using their id
    public void loadPlayer(int id) {
        this.player = this.savesTable.loadSave(id);
    }
}