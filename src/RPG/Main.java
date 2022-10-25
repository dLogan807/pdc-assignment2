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
    private Game game;
    private GameView gameView;
    private Player player;
    
    //Object to lock on
    private final Object lock = new Object();
    
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
        
        frameSetup.setIconImage(this.icon.getImage());
        frameSetup.setResizable(false);
        frameSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSetup.setContentPane(this.mainPanel);
        frameSetup.setSize(500, 800);
        
        return frameSetup;
    }
    
    //Update the CardLayout to show a panel
    private void updateCardLayout(JPanel panel) {
        this.mainPanel.removeAll();
        this.mainPanel.add(panel);
        this.mainPanel.repaint();
        this.mainPanel.revalidate();
    }
    
    //Initiates the menu MVC
    private void initiateMenu() {        
        this.menu = new Menu();
        
        this.menuView = new MenuView();
        this.menu.addObserver(menuView);
        
        MenuController menuController = new MenuController(this.menu, this.menuView, this.lock);
        this.menuView.addController(menuController);
    }
    
    //Initiates the game MVC
    private void initiateGame(Player player, boolean loaded) {
        this.game = new Game(player, loaded);
        
        this.gameView = new GameView();
        this.game.addObserver(gameView);
        
        GameController gameController = new GameController(this.game, gameView, this.lock);
        this.gameView.addController(gameController);
    }
    
    //Returns true if the player is still playing
    private boolean gameIsRunning() {
        return !this.game.getPlayer().getQuitFlag();
    }
    
    //Run the game
    public static void main(String[] args) {
        Main main = new Main();
        main.initiateMenu();
        main.updateCardLayout(main.menuView);
        
        main.frame.setVisible(true);
       
        //Main loop to handle the various MVCs
        while (true) {
            //Wait for a game to begin
            synchronized (main.lock) {
                while (main.menu.getPlayer() == null) {
                    try {
                        main.lock.wait(); }
                    catch (InterruptedException e) {
                        //Interrupt acts as exit
                        break;
                    }
                }
            }

            main.initiateGame(main.menu.getPlayer(), main.menu.getLoaded());
            main.updateCardLayout(main.gameView);
            
            //Wait for a game to end
            synchronized (main.lock) {
                while (main.gameIsRunning()) {
                    try {
                        main.lock.wait(); }
                    catch (InterruptedException e) {
                        //Interrupt acts as exit
                        break;
                    }
                }
            }
            
            main.menu.savePlayer();
            main.initiateMenu();
            main.updateCardLayout(main.menuView);
        }
    } 
}