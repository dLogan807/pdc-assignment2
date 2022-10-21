package RPG;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final String saveFile;
    private final String scoreFile;
    private final String directory;
    private Player player;
    private Game game;
    private final HashMap<String, Score> scores;
    
    //Menu constructor
    public Menu() {
        this.directory = "./save";
        this.saveFile = this.directory + "/save.txt";
        this.scoreFile = this.directory + "/scores.txt";
        this.scores = loadScores();
    }
    
    //File Handling functions:
    
    //Load scores from the scores file into a HashMap
    private HashMap<String, Score> loadScores() {
        HashMap<String, Score> scores = new HashMap();
        
        //If the score file exists
        if (directoryExists() && scoresExist()) {
            try {
                FileReader fr = new FileReader(this.scoreFile);
                BufferedReader inStream = new BufferedReader(fr);

                String input;
                String[] split;

                //Retrieve user data from the scores file and store it in a HashMap
                while((input = inStream.readLine()) != null) {
                    split = input.split(" ");
                    Score s = new Score(split[0], Integer.valueOf(split[1]));
                    scores.put(s.getName(), s);
                }
                inStream.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found!");
            }
            catch (IOException ex) {
                System.out.println("Error reading file!");
            }
        }
            
        return scores;
    }
    
    //Save all scores into the scores file
    private void saveScores() {
        //Add the score from the last game into the the HashMap
        this.scores.replace(this.player.getName(), new Score(this.player.getName(), calcScore()));
        this.scores.put(this.player.getName(), new Score(this.player.getName(), calcScore()));
        
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(this.scoreFile));
            
            //Write each score into the file
            for (Score s : this.scores.values()) {
                pw.println(s);
            }
            
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Scores file not found!");
        }
    }
    
    //Create the save directory
    public boolean createDirectory() {
        File f = new File(this.directory);
        return f.mkdir(); 
    }
    
    //Check if the save directory exists
    private boolean directoryExists() {
        return Files.isDirectory(Paths.get(this.directory));
    }        
    
    //Check if the save file exists
    private boolean saveExists() {
        return Files.exists(Paths.get(this.saveFile));
    }
    
    //Check if the scores file exists
    private boolean scoresExist() {
        return Files.exists(Paths.get(this.scoreFile));
    }
    
    //Save a player object to a file
    private void savePlayer() {
        try {
            //If the save directory doesn't exist
            if (!directoryExists()) {
                //Try to create the save directory
                if (!createDirectory()) {
                    System.out.println("Failed to create save directory!");
                    return;
                }
            }
            
            PrintWriter pw = new PrintWriter(new FileOutputStream(this.saveFile));
            
            //Convert the player into an ArrayList of Strings
            ArrayList<String> playerList = playerToArrayList(this.player);
            
            //Write the player to the file, overwriting old data
            for (String s : playerList) {
                pw.println(s);
            }
            
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Save not found!");
        }
    }
    
    //Store the contents of a Player into an ArrayList of Strings
    private ArrayList playerToArrayList(Player player) {
        ArrayList<String> playerList = new ArrayList();
        
        playerList.add(player.getName());
        playerList.add(player.getHealth() + "");
        playerList.add(player.getMoveCount() + "");
        playerList.add(player.getMonstersFought() + "");
        playerList.add(player.getItems().toString());
        
        return playerList;
    }
    
    //Load a Player object from the save file
    private Player loadPlayer() {
        String name = "";
        int health = 0, moveCount = 0, monstersFought = 0;
        HashSet<Item> items = new HashSet();
        
        try {
            FileReader fr = new FileReader(this.saveFile);
            BufferedReader inStream = new BufferedReader(fr);
            
            String input;
            int count = 0;
            
            //Retrieve player data from the save file
            while((input = inStream.readLine()) != null && count < 5) {
                switch (count) {
                    case 0:
                        name = input;
                        break;
                    case 1:
                        health = Integer.parseInt(input);
                        break;
                    case 2:
                        moveCount = Integer.parseInt(input);
                        break;
                    case 3:
                        monstersFought = Integer.parseInt(input);
                        break;
                    case 4:
                        items = stringToItems(input);
                        break;
                }
                count++;
            }
            
            inStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Save file not found!");
        }
        catch (IOException ex) {
            System.out.println("Error reading save file!");
        }
        
        //Return a player object with the loaded data
        return new Player(name, health, moveCount, monstersFought, items);
    }
    
    //Convert a string of items (from the player save) to a HashSet of Items
    private HashSet<Item> stringToItems(String stringItems) {
        stringItems = stringItems.replace('[', ' ');
        stringItems = stringItems.replace(']', ' ');
        stringItems = stringItems.trim();
        String[] splitItems = stringItems.split(",");
        
        HashSet<Item> items = new HashSet();
        Item item;
        
        for (String s : splitItems) {
            if (getItemName(s).equals(new DamagePotion().getName())) {
                item = new DamagePotion(getNumberHeld(s));
                items.add(item);
            } else if (getItemName(s).equals(new HealingPotion().getName())) {
                item = new HealingPotion(getNumberHeld(s));
                items.add(item);
            }
        }
        
        return items;
    }
    
    //Return the number of items held from a string (remove characters)
    private int getNumberHeld(String item) {
        char[] charArray = item.toCharArray();
        String count = "";
  
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i]))
                count = count + charArray[i];
        }
        
        return Integer.parseInt(count);
    }
    
    //Get the name of an item from a string (remove integers)
    private String getItemName(String item) {
        char[] charArray = item.toCharArray();
        String name = "";
  
        for (int i = 0; i < charArray.length; i++) {
            if (!Character.isDigit(charArray[i]))
                name = name + charArray[i];
        }
        
        return name.trim();
    }
    
    //File Handling functions end
    
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