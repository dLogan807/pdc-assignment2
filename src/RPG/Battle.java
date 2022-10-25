package RPG;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public final class Battle {
    private final Random rand;
    private final Player player;
    private final Monster monster;
    
    //Constructor for a battle
    public Battle(Player player, Monster monster) {
        this.rand = new Random();
        this.player = player;
        this.monster = monster;
        doBattle();
    }
    
    //Commence the battle
    private void doBattle() {
        
        boolean escaped;
        
        //While the monster has health
        while (this.monster.getHealth() > 0) {
            escaped = playerTurn();
            if (escaped) break; //If the player escapes successfully, exit
            if (this.player.getHealth() <= 0) break; //If the player dies, exit (they may die on their turn)
            if (this.player.getQuitFlag()) break; //If the player quits, exit
            
            if (this.monster.getHealth() > 0)
                monsterTurn();
            else {
                this.player.updateMonstersFought();
                System.out.println("You defeated the " + this.monster.getName() + "!");
            }
            if (this.player.getHealth() <= 0) break; //Monster killed the player
        }
    }
    
    //Display a list of battle actions to the player, returning whether they escaped
    private boolean playerTurn() {
        Scanner scan = new Scanner(System.in);
        int selection;
        
        while (true) {
            System.out.print("==== Health: ");
            if (this.player.getHealth() < 10)
                System.out.print(" ");
            System.out.println(this.player.getHealth() + "/20 ====");
            System.out.println("    Battle Actions");
            System.out.println("     1. Attack");
            System.out.println("     2. Use item");
            System.out.println("     3. Escape");
            System.out.println("     4. Quit");
            System.out.println("=======================");
            System.out.print("Enter your selection: ");

            try {
                selection = scan.nextInt();
                if (selection > 0 && selection <= 4)
                    break;
                else
                    System.out.println("\nInvalid input.\nPlease enter a number from the list of actions.\n");
            }
            catch (InputMismatchException e) {
                System.out.println("\nInvalid input.\nPlease enter a number from the list of actions.\n");
            }

            scan.nextLine();
        }
            
        //Do player action
        System.out.println();
        switch (selection) {
            case 1:
                this.player.attack(this.monster);
                break;
            case 2:
                useItem();
                break;
            case 3:
                System.out.println("You try to escape...");
                if (this.rand.nextBoolean()) {
                    System.out.println("And slip past the " + this.monster.getName() + "!");
                    return true;
                } else {
                    System.out.println("But the " + this.monster.getName() + " blocks you!");
                    break;
                }
            case 4:
                this.player.setQuitFlag(true);
                break;
        }
        
        System.out.println();
        
        //Player did not escape
        return false;
    } 
    
    //Do the monster's turn
    private void monsterTurn() {
        int actionChance = this.rand.nextInt(10);
        
        //80% chance to attack
        if (actionChance < 8) {
            this.monster.attack(this.player);
        } else
            this.monster.doNothing();   
        
        System.out.println();
    }
    
    //Use an item during battle (different outcomes than usual)
    private void useItem() {
        //Select an item to use
        System.out.println("You search through your backpack...\n");
        Item item = this.player.getPlayerItem();
        if (item == null) return;
        
        System.out.println("You grab the " + item.getName() + ".");
        
        int choice = player.doItemMenu(item);
        
        if (choice == 1)
            player.useItem(item, choice);
        else if (choice == 2)
            player.useItem(item, this.monster);
        else if (choice == 3)
            System.out.println("You zip up your backpack, fumbling about.");
        else if (choice == 4)
            player.setQuitFlag(true);
    }
}