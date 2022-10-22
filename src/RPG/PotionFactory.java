package RPG;

public class PotionFactory {
    //Return a Tile object dependent on the given string
    public static Potion getPotion(String className) {
        Potion p = null;
        
        if ("healing potion".equalsIgnoreCase(className))
            p = new HealingPotion();
        if ("damage potion".equalsIgnoreCase(className))
            p = new DamagePotion();
        
        return p;
    }
}