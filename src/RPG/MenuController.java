package RPG;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
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
        if (e.getActionCommand().equals("New Game")) {
            updateCardLayout(this.view.getNewGamePanel());
        }

        if (e.getActionCommand().equals("Begin Game")) {
            this.model.setPlayer(new Player(this.view.getNameTextField().getText()));
            this.model.setLoaded(false);
        }
        
        if (e.getActionCommand().equals("Load Game")) {
            updateCardLayout(this.view.getLoadGamePanel());
            showSaves(this.model.getSaves());
        }
            
        if (e.getActionCommand().equals("Load")) {
            this.model.loadPlayer(getSelectedSave());
            this.model.setLoaded(true);
        }
        
        if (e.getActionCommand().equals("View Scores")) {
            updateCardLayout(this.view.getViewScoresPanel());
            showScores(this.model.getScores());
        }
        
        if (e.getActionCommand().equals("Back")) {
            updateCardLayout(this.view.getMenuPanel());
        }     
        
        if (e.getActionCommand().equals("Quit"))
            System.exit(0);
    }
    
    //Update the CardLayout to show a panel
    private void updateCardLayout(JPanel panel) {
        this.view.removeAll();
        this.view.add(panel);
        this.view.repaint();
        this.view.revalidate();
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
    
    //Check whether a component is enabled
    public boolean isEnabled(JComponent component) {
        return component.isEnabled();
    }
    
    //Set the best score label to show the current best score
    public void setBestScore(String scoreDetails) {
        this.view.getBestScoreLabel().setText(scoreDetails);
    }
    
    //Display all past the scores
    public void showScores(String scores) {
        this.view.getScoresTextArea().setText(scores);
    }
    
    //Display all saves for loading in the form of a radio button selection
    public void showSaves(HashMap<Integer, Player> saves) {
        
        if (!saves.isEmpty()) {
            int y = 5;
            boolean firstButton = true;
            
            for (Player p : saves.values()) {
                JRadioButton radioButton = new JRadioButton(p.toString());
                radioButton.setActionCommand(p.toString());
                radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                
                //Set the first radio button as selected
                if (firstButton)
                    radioButton.setSelected(true);

                this.view.getSavesButtonGroup().add(radioButton);
                this.view.getSavesPanel().add(radioButton);
                
                radioButton.setBounds(10, y, 100, 30);
                
                y += 30;
            }
    
            this.view.getLoadButton().setEnabled(true);
            
            this.updateCardLayout(this.view.getLoadGamePanel());
        }
    }
}