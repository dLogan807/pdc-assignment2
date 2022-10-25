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
        int damage = this.rand.nextInt(4) + 1;
        
        //50% chance to use the more powerful ram attack
        boolean ramAttack = this.rand.nextBoolean();
            if (ramAttack) {
                this.monsterText = "The Slime rams you!";
                damage++;
            }
            else
                this.monsterText = "The Slime spits goo at you!";
            
        this.monsterText += "\n\nYou take " + damage + " damage!";
        
        player.updateHealth(0 - damage);
    }

    //The slime doesn't attack a player
    @Override
    public void doNothing() {
        this.monsterText = "The Slime bubbles angrily.";
    }
}