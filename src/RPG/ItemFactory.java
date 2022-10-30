package RPG;

public class ItemFactory {
    //Return an Item object dependent on the given string
    public static Item getItem(String className) {
        Item i = null;
        
        //If the class string contains "potion", call the potion factory
        if (className.toLowerCase().contains("potion"))
            i = PotionFactory.getPotion(className);
        
        return i;
    }
}