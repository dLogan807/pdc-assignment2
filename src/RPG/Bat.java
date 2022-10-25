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
        int damage = this.rand.nextInt(6);
        
        //50% chance to use the more powerful ram attack
        boolean ramAttack = this.rand.nextBoolean();
            if (ramAttack) {
                this.monsterText = "The Bat bites you!";
                damage++;
            }
            else
                this.monsterText = "The Bat slashes at you!";
        
        this.monsterText += "/n/nYou take " + damage + " damage!";
            
        player.updateHealth(0 - damage);
    }

    //When the bat is not attacking
    @Override
    public void doNothing() {
        this.monsterText = "The Bat issues a piercing sreech!";
    }
}