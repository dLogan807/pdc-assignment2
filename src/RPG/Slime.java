package RPG;

public class Slime extends Monster {
    //Slime constructor
    public Slime() {
        this.name = "Slime";
        this.health = 15;
    }

    //The slime attacks a player
    @Override
    public void attack(Player player) {
        int damage = rand.nextInt(4) + 1;
        
        //50% chance to use the more powerful ram attack
        boolean ramAttack = rand.nextBoolean();
            if (ramAttack) {
                monsterText = "The Slime rams you!";
                damage++;
            }
            else
                monsterText = "The Slime spits goo at you!";
            
        monsterText += "\n\nYou take " + damage + " damage!";
        
        player.updateHealth(0 - damage);
        
        if (player.isDead())
            monsterText += "\n\nThe Slime killed you...";
    }

    //The slime doesn't attack a player
    @Override
    public void doNothing() {
        monsterText = "The Slime bubbles angrily.";
    }
}