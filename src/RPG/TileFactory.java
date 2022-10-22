package RPG;

public class TileFactory {
    //Return a Tile object dependent on the given string
    public static Tile getTile(String className) {
        Tile t = null;
        
        if ("tile".equalsIgnoreCase(className))
            t = new Tile();
        if ("item".equalsIgnoreCase(className))
            t = new ItemTile();
        if ("chest".equalsIgnoreCase(className))
            t = new ChestTile();
        if ("trap".equalsIgnoreCase(className))
            t = new TrapTile();
        if ("monster".equalsIgnoreCase(className))
            t = new MonsterTile();
        
        return t;
    }
}