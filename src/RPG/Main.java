package RPG;

import java.awt.CardLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    private final URL iconURL;
    private final ImageIcon icon;
    
    private final JFrame frame;
    private final JPanel mainPanel;
    private Menu menu;
    private MenuView menuView;
    private Player player;
    
    //Main constructor
    public Main() {
        this.iconURL = getClass().getResource("resources/favicon.png");
        this.icon = new ImageIcon(iconURL);
        
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new CardLayout());
        
        this.frame = setupFrame();
    }
    
    //Sets up the main window - the JFrame
    private JFrame setupFrame() {
        JFrame frameSetup = new JFrame("Into the Depths");
        
        frameSetup.setIconImage(icon.getImage());
        frameSetup.setResizable(false);
        frameSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSetup.setContentPane(mainPanel);
        frameSetup.setSize(500, 800);
        
        return frameSetup;
    }
    
    //Update the CardLayout to show a panel
    private void updateCardLayout(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    //Initiates the menu MVC
    private void initiateMenu() {        
        menu = new Menu();
        menuView = new MenuView();
        MenuController menuController = new MenuController(menu, menuView);
        
        menuView.addController(menuController);
    }
    
    //Run the game
    public static void main(String[] args) {
        Main main = new Main();
        main.initiateMenu();
        main.updateCardLayout(main.menuView);
        
        main.frame.setVisible(true);
    } 
}