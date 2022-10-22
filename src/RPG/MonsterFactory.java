package RPG;

public class MonsterFactory {
    //Return a Monster object dependent on the given string
    public static Monster getMonster(String className) {
        Monster m = null;
        
        if ("bat".equalsIgnoreCase(className))
            m = new Bat();
        if ("slime".equalsIgnoreCase(className))
            m = new Slime();
        if ("spider".equalsIgnoreCase(className))
            m = new Spider();
        
        return m;
    }
}