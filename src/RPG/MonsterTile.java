package RPG;

public class MonsterTile extends Tile {
    private Monster monster;
    
    //MonsterTile constructor
    public MonsterTile() {
        this.eventTriggers = true;
        this.monster = generateMonster();
    }

    //Do the tile's event - begin a battle
    @Override
    public void doEvent(Player player) {
        System.out.println("You find yourself faced with a monster!\n");
        Battle battle = new Battle(player, this.monster);
    }
    
    //Return a random monster
    private Monster generateMonster() {
        int monsterSelect = this.getRand().nextInt(3);
        Monster monster;
        
        switch(monsterSelect) {
            case 1:
                monster = new Slime();
                break;
            case 2:
                monster = new Bat();
                break;
            default:
                monster = new Spider();
                break;
        }
        
        return monster;
    }
}