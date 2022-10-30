package RPG;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class BattleController extends Observable implements ActionListener  {
    private final Battle model;
    private final BattleView view;
    private final Random rand;
    
    //Constructor
    public BattleController(Battle model, BattleView view) {
        this.model = model;
        this.view = view;
        this.rand = new Random();
        
        showBattleIntro();
    }
    
    //Display text signifying a battle has started
    private void showBattleIntro() {   
        String text = "You find yourself faced with a monster!\n\n"
                        + "A " + model.getMonster().getName() + " approaches!";
        
        setBattleTextArea(text);
        
        setPlayerName();
        updateHealth();
        updateUseItemButton();
    }
    
    //Handle all button action events performed in the battle view
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "Attack":
                attack();
                doMonsterHandling();
                break;
            case "Escape":
                if (escaped())
                    endBattle("escaped");
                else
                    delayMonsterTurn();
                break;
            case "Use Item":
                if (itemPanelIsActive()) {
                    useSelectedItem();
                    if (model.getPlayer().isDead())
                        endBattle("player killed");
                    else
                        doMonsterHandling();
                } else {
                    updateCardLayout(view.getItemPanel());
                    showItems();
                }
                break;
            case "Throw Item":
                throwSelectedItem();
                doMonsterHandling();
                break;
            case "Back":
                updateCardLayout(view.getBattlePanel());
                setBattleTextArea("You zip up your backpack, fumbling about.");
                
                delayMonsterTurn();
                break;
            case "Exit to Menu":
                setChanged();
                notifyObservers("exited");
                break;
        }
    }

    //Set the text in the game's text area
    private void setBattleTextArea(String text) {
        view.getBattleTextArea().setText(text);
    }
    
     //Set the player's name on screen (crops if too long)
    private void setPlayerName() {
        String name = model.getPlayer().getCroppedName();
        
        view.getBattlePlayerNameLabel().setText(name);
        view.getItemPlayerNameLabel().setText(name);
    }
    
    //Update the value of the player's health on screen and change the text's colour
    private void updateHealth() {
        int health = model.getPlayer().getHealth();
        Color healthColour = model.getPlayer().getHealthColour();
        
        view.getBattleHealthLabel().setForeground(healthColour);
        view.getItemHealthLabel().setForeground(healthColour);
            
        view.getBattleHealthLabel().setText("Health: " + health + "/20");
        view.getItemHealthLabel().setText("Health: " + health + "/20");
    }
    
    //Enables/disables the Use Items button dependant on if a player has items
    private void updateUseItemButton() {
        view.getUseItemButton().setEnabled(model.getPlayer().hasItems());
    }
    
    //Update the CardLayout to show a panel
    private void updateCardLayout(JPanel panel) {
        view.removeAll();
        view.add(panel);
        view.repaint();
        view.revalidate();
    }
    
    //Returns true if the battle panel is showing
    private boolean itemPanelIsActive() {
        return view.getItemPanel().isShowing();
    }
    
    //The player attacks the monster
    private void attack() {
        model.getPlayer().attack(model.getMonster());
        setBattleTextArea(model.getPlayer().getPlayerText());
    }
    
    //Toggle whether the battle button can be used (exit button can still be)
    private void toggleBattleButtons() {
        boolean enabled = true;
        
        if (view.getAttackButton().isEnabled())
            enabled = false;
        
        view.getAttackButton().setEnabled(enabled);
        view.getEscapeButton().setEnabled(enabled);
        
        //Only enable the Use Item button if the player has items
        view.getUseItemButton().setEnabled(false);
        if (enabled)
            updateUseItemButton();
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
            
            y += 30;
            
            //Set the first JRadioButton as selected
            if (firstButton) {
                radioButton.setSelected(true);
                firstButton = false;
            }
        }
    }
    
    //Generates and returns a radio button with it's text set to the passed item's info
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
        setBattleTextArea(item.getItemText());
        
        updateHealth();
        updateUseItemButton();
        updateCardLayout(view.getBattlePanel());
    }
    
    //Throw the item the player has selected
    private void throwSelectedItem() {
        Item item = model.getPlayer().getItemFromName(getSelectedItemName());
        model.getPlayer().throwItem(item, model.getMonster());
        
        setBattleTextArea(item.getItemText());
        
        updateUseItemButton();
        updateCardLayout(view.getBattlePanel());
    }
    
    //Get the name of the selected item
    private String getSelectedItemName() {
        String[] itemDetails = view.getItemsButtonGroup().getSelection().getActionCommand().split(" ");

        return itemDetails[0] + " " + itemDetails[1];
    }
    
    //Returns true if the player is able to escape
    private boolean escaped() {
        boolean escaped = false;
        String text = "You try to escape...\n\n";
        
        if (rand.nextBoolean()) {
            text += "And slip past the " + model.getMonster().getName() + "!";
            escaped = true;
        } else {
            text += "But the " + model.getMonster().getName() + " blocks you!";
        }
        
        setBattleTextArea(text);
        
        return escaped;
    }
    
    //Exits the battle if the monster is dead and otherwise performs its turn
    public void doMonsterHandling() {
        if (model.getMonster().isDead()) {
            model.getPlayer().updateMonstersFought();
            endBattle("monster killed");
        } else
            delayMonsterTurn();
    }
    
    //Pause briefly before executing the monster's turn (prevents some button usage)
    private void delayMonsterTurn() {
        toggleBattleButtons();
        
        ActionListener doMonsterTurn = (ActionEvent e) -> {
            monsterTurn();
            toggleBattleButtons();
        };
        
        Timer timer = new Timer(1500, doMonsterTurn);
        timer.setRepeats(false);
        timer.start();
    }
    
    //Do the monster's turn
    private void monsterTurn() {
        int actionChance = rand.nextInt(10);
        
        //80% chance to attack
        if (actionChance < 8) {
            model.getMonster().attack(model.getPlayer());
        } else
            model.getMonster().doNothing();
        
        setBattleTextArea(model.getMonster().getMonsterText());
        
        if (model.getPlayer().isDead()) {
            endBattle("player killed");
        }
        
        updateHealth();
    }
    
    //Pause for a moment before notifying the Game that the battle completed
    private void endBattle(String battleResult) {
        toggleBattleButtons();
        
        ActionListener exitBattle = (ActionEvent e) -> {
            setChanged();
            notifyObservers(battleResult);
        };
        
        Timer timer = new Timer(1500, exitBattle);
        timer.setRepeats(false);
        timer.start();
    }
}