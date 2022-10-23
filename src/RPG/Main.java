package RPG;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.InputMismatchException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    private URL iconURL;
    private ImageIcon icon;
    
    private JFrame frame;
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
        JFrame frame = new JFrame("Into the Depths");
        
        frame.setIconImage(this.icon.getImage());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(480, 800);
        
        return frame;
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