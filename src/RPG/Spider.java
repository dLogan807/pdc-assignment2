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
                this.monsterText = "The Spider bites you!";
            }
            else
                this.monsterText = "The Spider charges at you!";
        
        this.monsterText += "\n\nYou take " + damage + " damage!";
            
        player.updateHealth(0 - damage);
    }

    //When the spider doesn't attack a player
    @Override
    public void doNothing() {
        this.monsterText = "The Spider bares its fangs and hisses menacingly!";
    }
}