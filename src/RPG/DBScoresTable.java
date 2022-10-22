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
        try {
            this.statement = conn.createStatement();
            this.statement.executeUpdate("CREATE TABLE " + this.tableName + "("
                                        + "score_id INT PRIMARY KEY,"
                                        + "name VARCHAR(200), "
                                        + "score INT)");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Return the data of the SCORES table as a HashMap of scores
    @Override
    protected HashMap getTableData() {
        ResultSet rs = null;
        HashMap<String, Score> scores = new HashMap();
        
        try {
            rs = this.statement.executeQuery("SELECT * FROM " + this.tableName);
            
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
        try {
            this.statement.executeUpdate("INSERT INTO " + this.tableName
                                        + " VALUES ("
                                        + score.getName() + ","
                                        + score.getScore() + ")");
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    public Score getBestScore() {
        ResultSet rs = null;
        Score bestScore = null;
        
        try {
            rs = this.statement.executeQuery("SELECT name, score"
                                            + " FROM " + this.tableName
                                            + " ORDER BY score DESC "
                                            + "LIMIT 1");
            
            bestScore = new Score(rs.getString("name"), rs.getInt("score"));
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return bestScore;
    }
}