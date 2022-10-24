package RPG;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameController implements ActionListener  {
    private Game model;
    private GameView view;
    private Random rand;
    
    public GameController(Game model, GameView view) {
        this.model = model;
        this.view = view;
        this.rand = new Random();
        
        showIntro();
    }
    
    //Display opening text dependant on whether a save was loaded
    private void showIntro() {       
        if (this.model.getLoaded()) {
            setGameTextArea("You resume your travels...");
        } else {
            String text = "Your goal in this game is to make "
                        + "it as far as possible into the "
                        + "dungeon. Good luck on your journey, "
                        + this.model.getPlayer().getName() + "."
                        + "\n\nYou enter the dungeon...";

            setGameTextArea(text);
        }
        
        updateHealth();
    }
    
    //Set the text in the game's text area
    private void setGameTextArea(String text) {
        this.view.getGameTextArea().setText(text);
    }
    
    //Update the value of the player's health on screen and change the text's colour
    private void updateHealth() {
        int health = this.model.getPlayer().getHealth();
        
        if (health < 5)
            this.view.getHealthLabel().setForeground(Color.RED);
        else if (health < 10)
            this.view.getHealthLabel().setForeground(Color.YELLOW);
        else
            this.view.getHealthLabel().setForeground(Color.WHITE);
        
        this.view.getHealthLabel().setText("Health: " + health + "/20");
    }
    
    //Handle all button action events performed in the menu view
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Continue Forward"))
            System.out.println("Forward!");
        
        if (e.getActionCommand().equals("Use Item"))
            System.out.println("Item!");
        
        if (e.getActionCommand().equals("Save & Exit to Menu")) {
            this.model.getPlayer().setQuitFlag(true);
        }
    }
    
    //Generate a new tile for the player as they "move forward"
    private void continueForward() {
        Tile tile;
        tile = generateTile();
        tile.doEvent(this.player);
        
        this.player.updateMoveCount();
    }
    
    //Display selection menus so that the player can use items
    private void useItem() {
        System.out.println("You search through your backpack...\n");
        
        //Select an item
        Item item = this.player.getPlayerItem();
        if (item == null) return;
        
        System.out.println("You grab the " + item.getName() + ".");
        
        //Get the player's choice for the retrieved item
        int choice = player.doItemMenu(item);
        
        if (choice == 4) {
            this.player.setQuitFlag(true);
        } else if (choice != 3)
            player.useItem(item, choice);
    }
    
    //Return a tile based on chance
    private Tile generateTile() {
        int tileChance = rand.nextInt(10);
        Tile tile;

        //10% chance to step on a trapped tile
        if (tileChance == 0)
            tile = TileFactory.getTile("trap");
        //10% chance to step on an item tile
        else if (tileChance  == 1)
            tile = new ItemTile();
        //40% chance to encounter a monster or a chest
        else if (tileChance > 1 && tileChance < 6) {
            int monsterOrChest = rand.nextInt(10);
            //70% chance for a monster, 30% chance for a chest
            if (monsterOrChest > 6)
                tile = TileFactory.getTile("chest");
            else
                tile = TileFactory.getTile("monster");
        //40% Chance for nothing to happen
        } else
            tile = TileFactory.getTile("tile");
        
        return tile;
    }
}