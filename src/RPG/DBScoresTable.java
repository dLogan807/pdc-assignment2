package RPG;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DBScoresTable extends DBTable {
    
    public DBScoresTable() {
        super();
        this.tableName = "SCORES";
        this.setupTable();
    }
    
    //Create the SCORES table if it doesn't already exist
    @Override
    protected void setupTable() {
        if (!tableExists(this.tableName))
            createTable();
    }
    
    //Create the scores table
    @Override
    protected void createTable() {
        dbManager.updateDB("CREATE TABLE " + this.tableName + "("
                        + "score_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "name VARCHAR(200), "
                        + "score INT)");
    }

    //Return the data of the SCORES table as a HashMap of scores
    @Override
    protected HashMap getTableData() {
        ResultSet rs = null;
        HashMap<String, Score> scores = new HashMap();
        
        try {
            rs = dbManager.queryDB("SELECT * FROM " + this.tableName);
            
            while (rs.next()) {
                scores.put(rs.getString("name"), new Score(rs.getString("name"), rs.getInt("score")));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return scores;
    }
    
    //Add a score to the SCORES table
    public void addScore(Score score) {
        dbManager.updateDB("INSERT INTO " + this.tableName + " (name, score)"
                        + " VALUES ("
                        + "'" + score.getName() + "'" + ","
                        + score.getScore() + ")");
    }
    
    //Get the highest score from the SCORES table
    public Score getBestScore() {
        ResultSet rs = null;
        Score bestScore = null;
        
        rs = dbManager.queryDB("SELECT name, score"
                        + " FROM " + this.tableName
                        + " WHERE score = (SELECT MAX(score) FROM " + this.tableName + ")");
        
        try {
            if (rs.next()) {
                bestScore = new Score(rs.getString("name"), rs.getInt("score"));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return bestScore;
    }
}