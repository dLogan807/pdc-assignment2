package RPG;

public class Score {
    private final String name;
    private final int score;
    
    //Score constructor
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //Get the name of the score
    public String getName() {
        return name;
    }

    //Get the score's value
    public int getScore() {
        return score;
    }
    
    //Check if two score objects are equal, using their names as a means to do so
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Score)) {
            return false;
        }
        Score other = (Score) o;

        return (this.getName().equals(other.getName()));
    }

    //Create hashCode from the score's name
    @Override
    public int hashCode() {
        int hashCode = 1;

        for (int i = 0; i < this.getName().length(); i++) {
            hashCode += this.getName().charAt(i);
        }
        hashCode = 781 * hashCode;

        return hashCode;
    }
    
    @Override
    public String toString() {
        return this.score + " " + this.name;
    }
}