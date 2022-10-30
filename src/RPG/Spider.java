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
        int damage = rand.nextInt(9) + 1;
        
        //50% to use either attack
        boolean ramAttack = rand.nextBoolean();
            if (ramAttack) {
                monsterText = "The Spider bites you!";
            }
            else
                monsterText = "The Spider charges at you!";
        
        monsterText += "\n\nYou take " + damage + " damage!";
            
        player.updateHealth(0 - damage);
        
        if (player.isDead())
            monsterText += "\n\nThe Spider killed you...";
        
    }

    //When the spider doesn't attack a player
    @Override
    public void doNothing() {
        monsterText = "The Spider bares its fangs and hisses menacingly!";
    }
}