package RPG;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BattleController implements ActionListener  {
    private Game model;
    private GameView view;
    private Random rand;
    
    private final Object lock;
    
    public BattleController(Game model, GameView view, Object lock) {
        this.model = model;
        this.view = view;
        this.rand = new Random();
        
        this.lock = lock;
        
        showIntro();
    }
    
    //Display opening text dependant on whether a save was loaded
    private void showIntro() {   
        String text;
        
        if (this.model.getLoaded()) {
            text = "You resume your travels...";
        } else {
            text = "Your goal in this game is to make "
                    + "it as far as possible into the "
                    + "dungeon. Good luck on your journey, "
                    + this.model.getPlayer().getName() + "."
                    + "\n\nYou enter the dungeon...";
        }
        
        text += "\n\n\nNote: Saving during a battle will not keep its progress.";
        setGameTextArea(text);
        
        setPlayerName();
        updateHealth();
        updateItemsButton();
    }
    
    //Set the text in the game's text area
    private void setGameTextArea(String text) {
        this.view.getGameTextArea().setText(text);
    }
    
    //Set the player's name on screen (crops if too long)
    private void setPlayerName() {
        String fullName = this.model.getPlayer().getName();
        int length = fullName.length();
        
        if (fullName.length() > 10)
            length = 10;
        
        String name = fullName.substring(0, length);
        
        this.view.getGamePlayerNameLabel().setText(name);
        this.view.getItemPlayerNameLabel().setText(name);
    }
    
    //Update the value of the player's health on screen and change the text's colour
    private void updateHealth() {
        int health = this.model.getPlayer().getHealth();
        
        if (health < 5) {
            this.view.getGameHealthLabel().setForeground(Color.RED);
            this.view.getItemHealthLabel().setForeground(Color.RED);
        } else if (health < 10) {
            this.view.getGameHealthLabel().setForeground(Color.YELLOW);
            this.view.getItemHealthLabel().setForeground(Color.YELLOW);
        } else {
            this.view.getGameHealthLabel().setForeground(Color.WHITE);
            this.view.getItemHealthLabel().setForeground(Color.WHITE);
        }
            
        this.view.getGameHealthLabel().setText("Health: " + health + "/20");
        this.view.getItemHealthLabel().setText("Health: " + health + "/20");
    }
    
    //Enables/disables the Use Items button dependant on if a player has items
    private void updateItemsButton() {
        this.view.getGameUseItemButton().setEnabled(this.model.getPlayer().hasItems());
    }
    
    //Handle all button action events performed in the menu view
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Continue Forward"))
            continueForward();
        
        if (e.getActionCommand().equals("Use Item")) {
            if (gamePanelIsActive()) {
                updateCardLayout(this.view.getItemPanel());
                showItems();
            } else {
                useItem();
            }
        }
        
        if (e.getActionCommand().equals("Throw Item"))
            throwItem();
        
        if (e.getActionCommand().equals("Back")) {
            updateCardLayout(this.view.getGamePanel());
            setAllText("You zip up your backpack.");
        }
        
        if (e.getActionCommand().equals("Save & Exit")) {
            this.model.getPlayer().setQuitFlag(true);
            
            releaseLock();
        }
    }
    
    //Update the CardLayout to show a panel
    private void updateCardLayout(JPanel panel) {
        this.view.removeAll();
        this.view.add(panel);
        this.view.repaint();
        this.view.revalidate();
    }
    
    //Notify the main thread that the game has begun
    private void releaseLock() {
        synchronized (this.lock) {
            this.lock.notifyAll();
        }
    }
    
    //Set the text area in all relevant panels to the same String
    private void setAllText(String text) {
        this.view.getGameTextArea().setText(text);
    }
    
    //Returns true if the game panel is showing
    private boolean gamePanelIsActive() {
        return this.view.getGamePanel().isShowing();
    }
    
    //Generate a new tile for the player as they "move forward"
    private void continueForward() {
        Tile tile;
        tile = generateTile();
        tile.doEvent(this.model.getPlayer());
        setAllText(tile.getEventText());
        
        this.model.getPlayer().updateMoveCount();
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
    
    //Show all the player's items in the form of selectable radio buttons
    private void showItems() {
        List<AbstractButton> radioButtonList = buttonGroupToList(this.view.getItemsButtonGroup());

        //Remove all current radio buttons from the panel
        for (AbstractButton rb : radioButtonList)
            this.view.getItemSelectItemPanel().remove(rb);
        
        boolean firstButton = true;
        int y = 5;
        
        for (Item i : this.model.getPlayer().getItems()) {
            JRadioButton radioButton = generateItemRadioButton(i);

            this.view.getItemsButtonGroup().add(radioButton);
            this.view.getItemSelectItemPanel().add(radioButton);
            radioButton.setBounds(10, y, 300, 30);
            y += 30;
            
            //Set the first JRadioButton as selected
            if (firstButton) {
                radioButton.setSelected(true);
                firstButton = false;
            }
        }
    }
    
    //Convert a Button Group into an iterable list
    private List<AbstractButton> buttonGroupToList(ButtonGroup group) {
        return Collections.list(group.getElements());
    }
    
    //Generates a radio button with it's text set to the passed item's info
    private JRadioButton generateItemRadioButton(Item item) {
        String radioText = item.getName() + " [" + item.getNumHeld() + " avail.]";
        
        JRadioButton radioButton = new JRadioButton(radioText);
        radioButton.setActionCommand(radioText);
        radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        radioButton.setBackground(Color.WHITE);
        
        return radioButton;
    }
    
    //Use the item the player has selected
    private void useItem() {
        Item item = this.model.getPlayer().getItemFromName(getSelectedItemName());
        this.model.getPlayer().useItem(item);
        setAllText(item.getItemText());
        
        updateHealth();
        updateItemsButton();
        updateCardLayout(this.view.getGamePanel());
    }
    
    //Throw the item the player has selected
    private void throwItem() {
        Item item = this.model.getPlayer().getItemFromName(getSelectedItemName());
        this.model.getPlayer().throwItem(item);
        setAllText(item.getItemText());
        
        updateHealth();
        updateItemsButton();
        updateCardLayout(this.view.getGamePanel());
    }
    
    //Get the name of the selected item
    private String getSelectedItemName() {
        String[] itemDetails = this.view.getItemsButtonGroup().getSelection().getActionCommand().split(" ");

        return itemDetails[0] + " " + itemDetails[1];
    }
}