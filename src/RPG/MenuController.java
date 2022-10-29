package RPG;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MenuController implements ActionListener, KeyListener, Observer  {
    private final Menu model;
    private final MenuView view;
    
    //Constructor
    public MenuController(Menu model, MenuView view) {
        this.model = model;
        this.view = view;
        
        setBestScore();
    }
    
    //Set up a game and display it
    private void initiateGame(Player player) {
        model.setGame(new Game(player));
        model.setGameView(new GameView());
        model.setGameController(new GameController(model.getGame(), model.getGameView()));

        model.getGameView().addController(model.getGameController());
        model.getGameController().addObserver(this);
        
        view.setGamePanel(model.getGameView());
        updateCardLayout(view.getGamePanel());
    }

    //Handle all button action events performed in the menu view
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "New Game":
                updateCardLayout(view.getNewGamePanel());
                break;
            case "Begin Game":
                model.setPlayer(new Player(view.getNameTextField().getText()));
                initiateGame(model.getPlayer());
                break;
            case "Load Game":
                showSaves(model.getSaves());
                updateCardLayout(view.getLoadGamePanel());
                break;
            case "Load":
                model.loadPlayer(getSelectedSave());
                initiateGame(model.getPlayer());
                break;
            case "View Scores":
                updateCardLayout(view.getViewScoresPanel());
                showScores(model.getScores());
                break;
            case "Back":
                updateCardLayout(view.getMenuPanel());
                break;
            case "Quit":
                System.exit(0);
                break;
        }   
    }
        
    //Unused method for if a key is typed
    @Override
    public void keyTyped(KeyEvent e) {}

    //Unused method for if a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {}

    //Handle an event if a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        updateBeginGameButton();
    }
    
    //Update the CardLayout to show a panel
    private void updateCardLayout(JPanel panel) {
        view.removeAll();
        view.add(panel);
        view.repaint();
        view.revalidate();
    }
    
    //Enable / disable the begin game button depending on whether a name has been typed
    private void updateBeginGameButton() {
        if (view.getNameTextField().getText().equals(""))
            view.getBeginGameButton().setEnabled(false);
        else
            view.getBeginGameButton().setEnabled(true);
    }
    
    //Returns the id of the selected save
    private int getSelectedSave() {
        int id;
        String[] saveString;
   
        saveString = view.getSavesButtonGroup().getSelection().getActionCommand().split(" ");
        id = Integer.parseInt(saveString[0]);
        
        return id;
    }
    
    //Check whether a component is enabled
    private boolean isEnabled(JComponent component) {
        return component.isEnabled();
    }
    
    //Set the best score label to show the current best score
    private void setBestScore() {
        view.getBestScoreLabel().setText(model.getBestScore());
    }
    
    //Display all past the scores
    private void showScores(String scores) {
        view.getScoresTextArea().setText(scores);
    }
    
    //Display all saves for loading in the form of a radio button selection
    private void showSaves(HashMap<Integer, Player> saves) {
        view.getSavesPanel().removeAll();
        
        boolean firstButton = true;
        
        int y = 5;
        int extraPanelHeight = 0;

        for (Player p : saves.values()) {
            JRadioButton radioButton = generateSaveRadioButton(p, y);

            view.getSavesButtonGroup().add(radioButton);
            view.getSavesPanel().add(radioButton);

            //Set the first JRadioButton as selected
            if (firstButton) {
                radioButton.setSelected(true);
                firstButton = false;
            }

            y += 30;

            //Prepare increase in JPanel height if needed
            if (y > 300) {
                extraPanelHeight += 25;
            }
        }

        increaseSavesPanelHeight(extraPanelHeight);

        updateLoadButton();
    }
    
    //Adds the specified height to the saves panel to allow for scrolling
    private void increaseSavesPanelHeight(int height) {
        view.getSavesPanel().setPreferredSize(new Dimension(268, (view.getSavesPanel().getHeight() + height)));
    }
    
    //Generates a radio button with it's text set to the player's id and name
    private JRadioButton generateSaveRadioButton(Player player, int y) {
        JRadioButton radioButton = new JRadioButton(player.toString());
        
        radioButton.setActionCommand(player.toString());
        radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        radioButton.setBackground(Color.WHITE);
        radioButton.setBounds(10, y, 200, 30);
        
        return radioButton;
    }
    
    //Enables / Disables the load button depending on whether saves are available
    public void updateLoadButton() {
        boolean enabled = !model.getSaves().isEmpty();
        
        view.getLoadButton().setEnabled(enabled);
    }

    //Handle how the game was exited
    @Override
    public void update(Observable o, Object arg) {
        String exitType = (String) arg;

        switch (exitType) {
            case "player killed":
                model.addScore();
                model.deletePlayerIfExists();
                updateCardLayout(view.getMenuPanel());
                setBestScore();
                break;
            case "saved and exited":
                model.savePlayer();
                updateCardLayout(view.getMenuPanel());
            case "exited":
                updateCardLayout(view.getMenuPanel());
        }
    }
}