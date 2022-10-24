package RPG;

import java.awt.BorderLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    private final URL iconURL;
    private final ImageIcon icon;
    
    private final JFrame frame;
    protected Menu menu;
    private Game game;
    private Player player;
    
    public Main() {
        this.iconURL = getClass().getResource("resources/favicon.png");
        this.icon = new ImageIcon(iconURL);
        
        this.menu = new Menu();
        
        this.frame = setupFrame();
    }
    
    private JFrame setupFrame() {
        JFrame frameSetup = new JFrame("Into the Depths");
        
        frameSetup.setIconImage(this.icon.getImage());
        frameSetup.setResizable(false);
        frameSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSetup.setLayout(new BorderLayout());
        frameSetup.setSize(480, 800);
        
        return frameSetup;
    }
    
    private void initiateMenu() {
        MenuView menuView = new MenuView();
        this.menu.addObserver(menuView);
        
        MenuController menuController = new MenuController(this.menu, menuView);
        menuView.addController(menuController);
        
        this.frame.add(menuView, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        
        main.initiateMenu();
        
        main.frame.setVisible(true);
    } 
}