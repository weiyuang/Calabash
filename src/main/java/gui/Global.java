package gui;

import creature.Creature;
import creature.HeroList;
import creature.MonsterList;

public class Global {
    public static int status;//0:ready  1:fighting  2:end

    public static HeroList herolist = new HeroList();
    public static MonsterList monsterlist = new MonsterList();
    public static Creature[][] battleMap;
    public static void initbattleMap(){
        battleMap = new Creature[20][11];
        for (int i=0;i<20;i++)
            for (int j=0;j<11;j++)
                battleMap[i][j]= new Creature();
    }

    public void clearbattleMap(){
        for (int i=0;i<20;i++)
            for (int j=0;i<11;j++)
                battleMap[i][j]= new Creature();
    }
}
