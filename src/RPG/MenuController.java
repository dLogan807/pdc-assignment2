package RPG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private Menu model;
    private MenuView view;
    
    public MenuController(Menu model, MenuView view) {
        this.model = model;
        this.view = view;
        
        this.setBestScore();
    }
    
    public void setBestScore() {
        this.view.bestScoreLabel.setText("dffs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New Game"))
            System.out.println("");
        else if (e.getActionCommand().equalsIgnoreCase("Load Game"))
            System.out.println("Loading!");
        else if (e.getActionCommand().equalsIgnoreCase("View Scores"))
            System.out.println("View Scores!");
        else {
        
        }
    }

    //Initiate the model
    public void initModel(int x) {
        //E.g. model.setValue(x);
    }
}