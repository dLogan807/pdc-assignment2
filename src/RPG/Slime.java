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
                System.out.println("The Slime rams you!");
                damage++;
            }
            else
                System.out.println("The Slime spits goo at you!");
        
        player.updateHealth(0 - damage);
    }

    //The slime doesn't attack a player
    @Override
    public void doNothing() {
        System.out.println("The Slime bubbles angrily.");
    }
}