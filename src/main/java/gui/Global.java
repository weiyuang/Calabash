package gui;

import creature.Creature;
import creature.HeroList;
import creature.MonsterList;

public class Global {
    public static int status;//0:ready  1:fighting  2:end
    public static int round = 0;

    public static HeroList herolist = new HeroList();
    public static MonsterList monsterlist = new MonsterList();
    public static Creature[][] battleMap;

    public static int WIDTH = 20;
    public static int HEIGHT = 11;
    public static int EMPTY = 0;
    public static int HERO = 1;
    public static int MONSTER = 2;
    public static int BODY = 3;

    public static void initbattleMap(){
        battleMap = new Creature[HEIGHT][WIDTH];
        for (int i=0;i<HEIGHT;i++)
            for (int j=0;j<WIDTH;j++)
                battleMap[i][j]= new Creature();
    }
}
