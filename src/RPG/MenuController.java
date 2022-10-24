package RPG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

public class MenuController implements ActionListener, KeyListener  {
    private Menu model;
    private MenuView view;
    
    public MenuController(Menu model, MenuView view) {
        this.model = model;
        this.view = view;
        
        this.setBestScore();
    }
    
    //Set the best score
    public void setBestScore() {
        setBestScore(this.model.getBestScore());
    }

    //Handle all button action events performed in the menu view
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New Game"))
            newGame();
        if (e.getActionCommand().equalsIgnoreCase("Begin Game"))
            this.model.setPlayer(new Player(this.view.getNameTextField().getText()));
        
        if (e.getActionCommand().equalsIgnoreCase("Load Game"))
            showSaves(this.model.getSaves());
        if (e.getActionCommand().equalsIgnoreCase("Load"))
            this.model.loadPlayer(getSelectedSave());
        
        if (e.getActionCommand().equalsIgnoreCase("View Scores"))
            showScores(this.model.getScores());
        
        if (e.getActionCommand().equalsIgnoreCase("Back"))
            back();        
        
        if (e.getActionCommand().equalsIgnoreCase("Quit"))
            System.exit(0);
    }
    
    //Unused method for if a key is typed
    @Override
    public void keyTyped(KeyEvent e) {}

    //Unused method for if a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {}

    //Enable / disable the begin game button depending on whether a name has been typed
    @Override
    public void keyReleased(KeyEvent e) {
        if (this.view.getNameTextField().getText().equals("")) {
            this.view.getBeginGameButton().setEnabled(false);
        } else {
            this.view.getBeginGameButton().setEnabled(true);
        }
    }
    
    //Returns the id of the selected save
    public int getSelectedSave() {
        int id;
        String[] saveString;
        
        saveString = this.view.getSavesButtonGroup().getSelection().getActionCommand().split(" ");
        id = Integer.parseInt(saveString[0]);
        
        return id;
    }

    //Toggles the visiblity of a component
    public void toggleVisibility(JComponent component) {
        if (component.isVisible())
            component.setVisible(false);
        else
            component.setVisible(true);
    }
    
    //Check whether a component is enabled
    public boolean isEnabled(JComponent component) {
        return component.isEnabled();
    }
    
    //Toggle whether the menu buttons are visible
    public void toggleMenuButtons() {
        toggleVisibility(this.view.getNewGameButton());
        toggleVisibility(this.view.getLoadGameButton());
        toggleVisibility(this.view.getViewScoresButton());
        toggleVisibility(this.view.getQuitButton());
    }
    
    //Set the best score label to show the current best score
    public void setBestScore(String scoreDetails) {
        this.view.getBestScoreLabel().setText(scoreDetails);
    }
    
    //Show the interface for starting a game
    public void newGame() {
        toggleMenuButtons();
        toggleVisibility(this.view.getBestScoreHeaderLabel());
        toggleVisibility(this.view.getBestScoreLabel());
        
        toggleVisibility(this.view.getNameLabel());
        toggleVisibility(this.view.getNameTextField());
        toggleVisibility(this.view.getBeginGameButton());
        
        toggleVisibility(this.view.getBackButton());
    }
    
    //Display all past the scores
    public void showScores(String scores) {
        toggleMenuButtons();
        
        this.view.getScoresTextArea().setText(scores);
        toggleVisibility(this.view.getScoresScrollPane());
        
        toggleVisibility(this.view.getBackButton());
    }
    
    //Display all saves for loading in the form of a radio button selection
    public void showSaves(HashMap<Integer, Player> saves) {
        this.view.setSavesButtonGroup(new ButtonGroup());
        
        if (!saves.isEmpty()) {
            for (Player p : saves.values()) {
                JRadioButton radioButton = new JRadioButton(p.toString());

                this.view.getSavesButtonGroup().add(radioButton);
                this.view.getSavesScrollPane().add(radioButton);
            }
            
            this.view.getLoadButton().setEnabled(true);
        }
        
        toggleMenuButtons();
        toggleVisibility(this.view.getBestScoreHeaderLabel());
        toggleVisibility(this.view.getBestScoreLabel());
        
        toggleVisibility(this.view.getSavesScrollPane());
        toggleVisibility(this.view.getLoadButton());
        toggleVisibility(this.view.getBackButton());
    }
    
    //Return to the menu
    public void back() {
        if (this.view.getScoresScrollPane().isVisible())
            toggleVisibility(this.view.getScoresScrollPane());
        else if (this.view.getSavesScrollPane().isVisible()) {
            toggleVisibility(this.view.getBestScoreHeaderLabel());
            toggleVisibility(this.view.getBestScoreLabel());
            
            toggleVisibility(this.view.getSavesScrollPane());
            this.view.getLoadButton().setEnabled(false);
            toggleVisibility(this.view.getLoadButton());
        }
        else if (this.view.getNameLabel().isVisible()) {
            toggleVisibility(this.view.getBestScoreHeaderLabel());
            toggleVisibility(this.view.getBestScoreLabel());

            toggleVisibility(this.view.getNameLabel());
            toggleVisibility(this.view.getNameTextField());
            toggleVisibility(this.view.getBeginGameButton());
        }

        toggleVisibility(this.view.getBackButton());
        toggleMenuButtons();
    } 
}