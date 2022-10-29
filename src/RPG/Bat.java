package RPG;

public class Bat extends Monster {
    //Bat constructor
    public Bat() {
        this.name = "Bat";
        this.health = 10;
    }

    //When the bat attacks a player
    @Override
    public void attack(Player player) {
        int damage = rand.nextInt(6);
        
        //50% chance to use the more powerful ram attack
        boolean ramAttack = rand.nextBoolean();
            if (ramAttack) {
                monsterText = "The Bat bites you!";
                damage++;
            }
            else
                monsterText = "The Bat slashes at you!";
        
        monsterText += "\n\nYou take " + damage + " damage!";
            
        player.updateHealth(0 - damage);
        
        if (player.isDead())
            monsterText += "\n\nThe Bat killed you...";
    }

    //When the bat is not attacking
    @Override
    public void doNothing() {
        monsterText = "The Bat issues a piercing sreech!";
    }
}