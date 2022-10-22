package RPG;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public final class Game {
    private final Player player;
    private Random rand;
    private Scanner scan;
    private int selection;
    
    //Construct a game
    public Game(Player player, boolean loaded) {
        this.player = player;
        this.rand = new Random();
        this.scan = new Scanner(System.in);
        this.selection = 0;
        doGame(loaded);
    }
    
    private void doGame(boolean loaded) {       
        //Skip intro if loading from a save
        if (loaded) {
            System.out.println("\nYou resume your travels...\n");
        } else {
            System.out.println("=======================");
            System.out.println("Your goal in this game");
            System.out.println("is to make it as far as");
            System.out.println("possible into the");
            System.out.println("dungeon. Good luck on");
            System.out.println("your journey, traveller");
            System.out.println(player.getName() + ".");
            System.out.println("=======================");

            System.out.println("\nYou enter the dungeon...\n");
        }
        
        //Loop the game menu
        gameMenu: while (true) {
            while (true) {
                System.out.print("==== Health: ");
                if (this.player.getHealth() < 10)
                    System.out.print(" ");
                System.out.println(this.player.getHealth() + "/20 ====");
                System.out.println("        Actions");
                System.out.println("  1. Continue forward");
                System.out.println("  2. Use item");
                System.out.println("  3. Save & Exit to menu");
                System.out.println("  4. Quit");
                System.out.println("=======================");
                System.out.print("Enter your selection: ");
                
                try {
                    this.selection = this.scan.nextInt();
                    if (this.selection > 0 && this.selection <= 4)
                        break;
                    else
                        System.out.println("\nInvalid input.\nPlease enter a number from the list of actions.\n");
                }
                catch (InputMismatchException e) {
                    System.out.println("\nInvalid input.\nPlease enter a number from the list of actions.\n");
                }

                this.scan.nextLine();
            }
            
            System.out.println();
            switch (this.selection) {
                case 1:
                    continueForward();
                    break;
                case 2:
                    useItem();
                    break;
                case 3:
                    break gameMenu;
                case 4:
                    this.player.setQuitFlag(true);
                    break gameMenu;
            }
            
            if (this.player.getHealth() <= 0) break; //Exit as the player has died
            if (this.player.getQuitFlag()) break; //Exit as quit specified
            
            System.out.println();
        }
    }
    
    //Generate a new tile for the player as they "move forward"
    private void continueForward() {
        Tile tile;
        tile = generateTile();
        tile.doEvent(this.player);
        
        this.player.updateMoveCount();
    }
    
    //Display selection menus so that the player can use items
    private void useItem() {
        System.out.println("You search through your backpack...\n");
        
        //Select an item
        Item item = this.player.getPlayerItem();
        if (item == null) return;
        
        System.out.println("You grab the " + item.getName() + ".");
        
        //Get the player's choice for the retrieved item
        int choice = player.doItemMenu(item);
        
        if (choice == 4) {
            this.player.setQuitFlag(true);
        } else if (choice != 3)
            player.useItem(item, choice);
    }
    
    //Return a tile based on chance
    private Tile generateTile() {
        int tileChance = rand.nextInt(10);
        Tile tile;

        //10% chance to step on a trapped tile
        if (tileChance == 0)
            tile = TileFactory.getTile("trap");
        //10% chance to step on an item tile
        else if (tileChance  == 1)
            tile = new ItemTile();
        //40% chance to encounter a monster or a chest
        else if (tileChance > 1 && tileChance < 6) {
            int monsterOrChest = rand.nextInt(10);
            //70% chance for a monster, 30% chance for a chest
            if (monsterOrChest > 6)
                tile = TileFactory.getTile("chest");
            else
                tile = TileFactory.getTile("monster");
        //40% Chance for nothing to happen
        } else
            tile = TileFactory.getTile("tile");
        
        return tile;
    }
}