package RPG;

public class ItemFactory {
    //Return an Item object dependent on the given string
    public static Item getItem(String className) {
        Item i = null;
        
        if (className.toLowerCase().contains("potion"))
            i = PotionFactory.getPotion(className);
        
        return i;
    }
}