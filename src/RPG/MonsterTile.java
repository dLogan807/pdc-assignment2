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
        return monster;
    }
    
    //Return a random monster
    private Monster generateMonster() {
        int monsterSelect = getRand().nextInt(3);
        Monster generateMonster;
        
        switch(monsterSelect) {
            case 1:
                generateMonster = MonsterFactory.getMonster("bat");
                break;
            case 2:
                generateMonster = MonsterFactory.getMonster("slime");
                break;
            default:
                generateMonster = MonsterFactory.getMonster("spider");
                break;
        }
        
        return generateMonster;
    }
}