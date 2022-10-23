package RPG;

import java.util.HashMap;
import java.util.Observable;

public class Menu extends Observable {
    private Player player;
    private DBSavesTable savesTable;
    private DBScoresTable scoresTable;
    
    public Menu() {
        this.savesTable = new DBSavesTable();
        this.scoresTable = new DBScoresTable();
    }
    
    //Calculate the player's score
    private int calcScore() {
        return player.getMoveCount() + player.getMonstersFought() * 10;
    }
    
    //Return a String of player scores
    private String getScores() {
        HashMap<String, Score> scores = this.scoresTable.getTableData();
        String scoreString = "";
        
        if (scores.isEmpty())
            scoreString = "No Scores";
        else {
            for (Score s : scores.values())
                scoreString += s + "\n";
        }
        
        return scoreString;
    }
    
    //Get the best game score
    private String getBestScore() {
        return this.scoresTable.getBestScore().toString();
    }
}