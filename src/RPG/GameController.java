package RPG;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class GameController extends Observable implements ActionListener, Observer  {
    private Game model;
    private GameView view;
    private Random rand;
    
    //Constructor
    public GameController(Game model, GameView view) {
        this.model = model;
        this.view = view;
        this.rand = new Random();
        
        showIntro();
    }
    
    //Display opening text dependant on whether a save was loaded
    private void showIntro() {   
        String text;
        
        if (model.getPlayer().isLoaded()) {
            text = "You resume your travels...";
        } else {
            text = "Your goal in this game is to make "
                    + "it as far as possible into the "
                    + "dungeon. Good luck on your journey, "
                    + model.getPlayer().getName() + "."
                    + "\n\nYou enter the dungeon..."
                    + "\n\nNotes:\n - Your save will be deleted if you die!"
                    + "\n - Games cannot be saved during a battle";
        }
        
        setGameTextArea(text);
        
        setPlayerName();
        updateHealth();
        updateItemsButton();
    }
    
    //Set up the game model's battle and display it
    private void initiateBattle(Player player, Monster monster) {
        model.setBattle(new Battle(player, monster));
        model.setBattleView(new BattleView());
        model.setBattleController(new BattleController(model.getBattle(), model.getBattleView()));

        model.getBattleView().addController(model.getBattleController());
        model.getBattleController().addObserver(this);
        
        view.setBattlePanel(model.getBattleView());
        updateCardLayout(view.getBattlePanel());
    }
    
    //Handle all button action events performed in the game view
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "Continue Forward":
                continueForward();
                break;
            case "Use Item":
                if (gamePanelIsActive()) {
                    updateCardLayout(view.getItemPanel());
                    showItems();
                } else {
                    useSelectedItem();
                    if (model.getPlayer().isDead())
                        endGameDelay("player killed");
                }
                break;
            case "Throw Item":
                throwSelectedItem();
                break;
            case "Back":
                updateCardLayout(view.getGamePanel());
                setGameTextArea("You zip up your backpack.");
                break;
            case "Save & Exit":
                setChanged();
                notifyObservers("saved and exited");
                break;
        }
    }
    
    //Set the text in the game's text area
    private void setGameTextArea(String text) {
        view.getGameTextArea().setText(text);
    }
    
    //Set the player's name on screen (crops if too long)
    private void setPlayerName() {
        String name = model.getPlayer().getCroppedName();
        
        view.getGamePlayerNameLabel().setText(name);
        view.getItemPlayerNameLabel().setText(name);
    }
    
    //Update the value of the player's health on screen and change the text's colour
    private void updateHealth() {
        int health = model.getPlayer().getHealth();
        Color healthColour = model.getPlayer().getHealthColour();
        
        view.getGameHealthLabel().setForeground(healthColour);
        view.getItemHealthLabel().setForeground(healthColour);
            
        view.getGameHealthLabel().setText("Health: " + health + "/20");
        view.getItemHealthLabel().setText("Health: " + health + "/20");
    }
    
    //Enables/disables the Use Items button dependant on if a player has items
    private void updateItemsButton() {
        view.getGameUseItemButton().setEnabled(model.getPlayer().hasItems());
    }
    
    //Update the CardLayout to show a panel
    private void updateCardLayout(JPanel panel) {
        view.removeAll();
        view.add(panel);
        view.repaint();
        view.revalidate();
    }
    
    //Returns true if the game panel is showing
    private boolean gamePanelIsActive() {
        return view.getGamePanel().isShowing();
    }
    
    //Generate a new tile for the player as they "move forward"
    private void continueForward() {
        model.getPlayer().updateMoveCount();
        
        Tile tile;
        tile = generateTile();
        tile.doEvent(model.getPlayer());
        
        setGameTextArea(tile.getEventText());
        
        if (tile instanceof MonsterTile)
            initiateBattle(model.getPlayer(), ((MonsterTile) tile).getMonster());
        
        if (model.getPlayer().isDead())
            endGameDelay("player killed");
        
        updateHealth();
        updateItemsButton();
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
        view.getItemSelectItemPanel().removeAll();
        
        boolean firstButton = true;
        int y = 5;
        
        for (Item i : model.getPlayer().getItems()) {
            JRadioButton radioButton = generateItemRadioButton(i, y);

            view.getItemsButtonGroup().add(radioButton);
            view.getItemSelectItemPanel().add(radioButton);
            
            //Set the first Item as selected
            if (firstButton) {
                radioButton.setSelected(true);
                firstButton = false;
            }
            
             y += 30;
        }
    }
    
    //Generates a radio button with it's text set to the passed item's info
    private JRadioButton generateItemRadioButton(Item item, int y) {
        String radioText = item.getName() + " [" + item.getNumHeld() + " avail.]";
        
        JRadioButton radioButton = new JRadioButton(radioText);
        radioButton.setActionCommand(radioText);
        radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        radioButton.setBackground(Color.WHITE);
        radioButton.setBounds(10, y, 300, 30);
        
        return radioButton;
    }
    
    //Use the item the player has selected
    private void useSelectedItem() {
        Item item = model.getPlayer().getItemFromName(getSelectedItemName());
        model.getPlayer().useItem(item);
        setGameTextArea(item.getItemText());
        
        updateHealth();
        updateItemsButton();
        updateCardLayout(view.getGamePanel());
    }
    
    //Throw the item the player has selected
    private void throwSelectedItem() {
        Item item = model.getPlayer().getItemFromName(getSelectedItemName());
        model.getPlayer().throwItem(item);
        setGameTextArea(item.getItemText());
        
        updateHealth();
        updateItemsButton();
        updateCardLayout(view.getGamePanel());
    }
    
    //Get the name of the selected item
    private String getSelectedItemName() {
        String[] itemDetails = view.getItemsButtonGroup().getSelection().getActionCommand().split(" ");

        return itemDetails[0] + " " + itemDetails[1];
    }
    
    //Toggle whether the game's buttons are enabled
    public void toggleGameButtons() {
        boolean enabled = true;
        
        if (view.getContinueFowardButton().isEnabled())
            enabled = false;
        
        view.getContinueFowardButton().setEnabled(enabled);
        view.getGameUseItemButton().setEnabled(enabled);
        view.getSaveAndExitButton().setEnabled(enabled);
    }

    //Handle how to exit a battle
    @Override
    public void update(Observable o, Object arg) {
        String exitType = (String) arg;
        
        switch(exitType) {
            case "monster killed":
                updateCardLayout(view.getGamePanel());
                updateHealth();
                updateItemsButton();
                break;
            case "escaped":
                updateCardLayout(view.getGamePanel());
                updateHealth();
                updateItemsButton();
                break;
            default:
                setChanged();
                notifyObservers(exitType);
                break;
        }
    }
    
    //Pause for a moment before notifying the Menu that the Game has been exited
    private void endGameDelay(String exit) {
        toggleGameButtons();
        
        ActionListener exitBattle = (ActionEvent e) -> {
            setChanged();
            notifyObservers(exit);
        };
        
        Timer timer = new Timer(1500, exitBattle);
        timer.setRepeats(false);
        timer.start();
    }
}