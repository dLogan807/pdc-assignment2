package RPG;

public class MonsterTile extends Tile {
    private Monster monster;
    
    //MonsterTile constructor
    public MonsterTile() {
        this.eventTriggers = true;
        this.monster = generateMonster();
    }
    
    //Return the monster
    public Monster getMonster() {
        return this.monster;
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