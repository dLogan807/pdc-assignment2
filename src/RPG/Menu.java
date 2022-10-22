package RPG;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Player player;
    private Game game;
    private final HashMap<String, Score> scores;
    
    //Menu constructor
    public Menu() {

    }
    
    //Calculate the player's score
    private int calcScore() {
        return player.getMoveCount() + player.getMonstersFought() * 10;
    }
    
    //Display a list of player scores
    private void viewScores() {
        System.out.println("\n======= Scores ========");
        
        if (this.scores.isEmpty())
            System.out.println("\n      No Scores      \n");
        
        for (Score s : this.scores.values()) {
            System.out.println("       " + s);
        }
        System.out.println("=======================");
        System.out.println();
    }
    
    //Main method - Handles the RPG's main menu
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();

        boolean invalid = true;
        int selection = 0;
        boolean loopMenu;
        
        System.out.println("- - Into the Depths - -");
        System.out.println(" A short RPG adventure ");
        
        //Loop the menu
        main: while (true) {
            loopMenu = false;
            
            //Get user input
            while (true) {
                System.out.println("=======================");
                System.out.println("          Menu");
                System.out.println("     1. New Game");
                System.out.println("     2. Load Game");
                System.out.println("     3. View Scores");
                System.out.println("     4. Quit");
                System.out.println("=======================");
                System.out.print("Enter your selection: ");

                try {
                    selection = scan.nextInt();
                    if (selection > 0 && selection <= 4)
                        break;
                    else
                        System.out.println("Invalid input.\nPlease enter a number from the menu.");
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input.\nPlease enter a number from the menu.");
                }

                scan.nextLine();
            }
            
            //Perform actions based user selection
            switch (selection) {
                //Start a new game
                case 1:
                    String name;
                    scan.nextLine();
                    
                    //Get player name (allows quitting!)
                    while (true) {
                        System.out.println("Enter your name (or \"quit\" to quit): ");
                        name = scan.nextLine();
                        
                        if (name.toLowerCase().equals("quit")) {
                            System.out.println("\nThanks for playing!\n");
                            break main;
                        }
                        
                        if (name.length() > 0)
                            break;
                        else 
                            System.out.println("Please enter at least one character.");
                    }
                    
                    menu.player = new Player(name);
                    menu.game = new Game(menu.player, false);
                    break;
                //Load a game
                case 2:
                    if (menu.saveExists()) {
                        menu.player = menu.loadPlayer();
                        menu.game = new Game(menu.player, true);
                    } else {
                        System.out.println("\nNo save file found.\n");
                        loopMenu = true;
                    }
                    break;
                //Display player scores
                case 3:
                    menu.viewScores();
                    loopMenu = true;
                    break;
                //Quit the game
                case 4:
                    System.out.println("\nThanks for playing!\n");
                    break main;
            }
            
            //If the menu hasn't been set to loop
            if (!loopMenu) {
                //Quit if player quitting flag was specified
                if (menu.player.getQuitFlag()) {
                    System.out.println("\nThanks for playing!\n");
                    break;
                } 
                //Player died - display their score and save it
                else if (menu.player.getHealth() <= 0) {
                        System.out.println("=======================");
                        System.out.println("       You Died");
                        System.out.println("       Score: " + menu.calcScore());
                        System.out.println("=======================\n");

                        menu.saveScores();
                } 
                //Save the game
                else {
                    menu.savePlayer();
                    System.out.println("Game saved!\n");
                }
            }
        }
    }
}