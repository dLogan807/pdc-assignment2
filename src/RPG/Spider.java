package RPG;

public class Spider extends Monster {
    //Spider constructor
    public Spider() {
        this.name = "Spider";
        this.health = 20;
    }

    //When spider attacks a player
    @Override
    public void attack(Player player) {
        int damage = this.rand.nextInt(9) + 1;
        
        //50% to use either attack
        boolean ramAttack = this.rand.nextBoolean();
            if (ramAttack) {
                System.out.println("The Spider bites you!");
            }
            else
                System.out.println("The Spider charges at you!");
        
        player.updateHealth(0 - damage);
    }

    //When the spider doesn't attack a player
    @Override
    public void doNothing() {
        System.out.println("The Spider bares its fangs and");
        System.out.println("hisses menacingly!");
    }
}