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
        this.eventText = "You find yourself faced with a monster!\n\n"
                        + "A " + this.monster.getName() + " approaches!";
        
        //Battle battle = new Battle(player, this.monster);
    }
    
    //Return a random monster
    private Monster generateMonster() {
        int monsterSelect = this.getRand().nextInt(3);
        Monster monster;
        
        switch(monsterSelect) {
            case 1:
                monster = MonsterFactory.getMonster("bat");
                break;
            case 2:
                monster = MonsterFactory.getMonster("slime");
                break;
            default:
                monster = MonsterFactory.getMonster("spider");
                break;
        }
        
        return monster;
    }
}