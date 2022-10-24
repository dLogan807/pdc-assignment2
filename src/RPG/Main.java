package RPG;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    private final URL iconURL;
    private final ImageIcon icon;
    
    private final JFrame frame;
    private CardLayout cards;
    protected Menu menu;
    protected MenuView menuView;
    private Game game;
    private GameView gameView;
    private Player player;
    
    //Main constructor
    public Main() {
        this.iconURL = getClass().getResource("resources/favicon.png");
        this.icon = new ImageIcon(iconURL);
        
        this.frame = setupFrame();
    }
    
    //Sets up the main window - the JFrame
    private JFrame setupFrame() {
        JFrame frameSetup = new JFrame("Into the Depths");
        
        frameSetup.setIconImage(this.icon.getImage());
        frameSetup.setResizable(false);
        frameSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSetup.setSize(480, 800);
        frameSetup.setVisible(true);
        
        return frameSetup;
    }
    
    //Updates the layout to a new one
    private void setNewLayout() {
        this.cards = new CardLayout();
        this.frame.getContentPane().setLayout(cards);
    }
    
    //Initiates the menu MVC
    private void initiateMenu() {
        setNewLayout();
        
        this.menu = new Menu();
        
        this.menuView = new MenuView();
        this.menu.addObserver(menuView);
        
        MenuController menuController = new MenuController(this.menu, this.menuView);
        this.menuView.addController(menuController);
        
        this.frame.add(this.menuView);
        this.cards.first(this.frame.getContentPane());
    }
    
     //Initiates the game MVC
    private void initiateGame(Player player, boolean loaded) {
        this.game = new Game(player, loaded);
        
        this.gameView = new GameView();
        this.game.addObserver(gameView);
        
        GameController gameController = new GameController(this.game, gameView);
        this.gameView.addController(gameController);
        
        this.frame.add(this.gameView);
        this.cards.next(this.frame.getContentPane());
    }
    
    //Update the CardLayout to show a panel
    private void updateCardLayout(JPanel panel) {
        this.frame.removeAll();
        this.frame.add(panel);
        this.frame.repaint();
        this.frame.revalidate();
    }
    
    //Returns true if the player is still playing
    public boolean gameIsRunning() {
        return !this.game.getPlayer().getQuitFlag();
    }
    
    //Run the game
    public static void main(String[] args) {
        Main main = new Main();
        main.initiateMenu();
       
        //Main loop to handle the various MVCs
        while (true) {
            //If the game has begun
            if (main.menu.getPlayer() != null) {
                main.initiateGame(main.menu.getPlayer(), main.menu.getLoaded());

                //If the player has quit
                if (!main.gameIsRunning()) {
                    main.menu.savePlayer();
                    main.initiateMenu();
                }
            }
        }
    } 
}