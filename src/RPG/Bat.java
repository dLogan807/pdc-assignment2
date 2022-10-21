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
                System.out.println("The Bat bites you!");
                damage++;
            }
            else
                System.out.println("The Bat slashes at you!");
        
        player.updateHealth(0 - damage);
    }

    //When the bat is not attacking
    @Override
    public void doNothing() {
        System.out.println("The Bat issues a piercing sreech!");
    }
}