package creature;

import javafx.scene.image.Image;

import java.util.Random;

import static gui.Global.battleMap;
import static gui.Global.herolist;
import static gui.Global.monsterlist;


public class Creature {
    protected String name;
    Image image;
    public boolean camp;//0:bad 1：good
    protected boolean state;//0：dead  1：alive

    public Creature() {
        name = null;
    }

    protected Position position = new Position();

    public String getName() {
        return name;
    }

    public void setPosition(int x, int y) {
        this.position.setPosition(x, y);
    }

    public Image getImage() {
        return this.image;
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public void move(){
        Random random = new Random();
        int desX=getX(), desY=getY();
        int diretion = random.nextInt(4);
        if (diretion == 0){ desX--;}
        else if (diretion == 1){ desY--;}
        else if (diretion == 2){ desX++;}
        else {desY++;}
        if (desX<20 && desX > 0 & desY < 11 & desY > 0){
            if(this.camp != battleMap[desX][desY].camp || battleMap[desX][desY].getName()==null) {
                setPosition(desX,desY);
                battleMap[desX][desY] = battleMap[getX()][getY()];
                battleMap[getX()][getY()] = new Creature();
            }
        }
    }
    /*public void randomMove() {
        Random random = new Random();
        int diretion = random.nextInt(4);//0,左，1，上，2，右，3，下
        boolean isKill = false;
        if (camp) {
            if (diretion == 0) {
                if (getX() > 0 && !herolist.isOccupied(getX()-1,getY()))
                {
                    setPosition(getX()-1,getY());
                }
            } else if (diretion == 1) {
                if (getY() > 0 && !herolist.isOccupied(getX(),getY()-1))
                {
                    setPosition(getX(),getY()-1);
                }
            } else if (diretion == 2) {
                //System.out.println("start"+getX()+getY());
                //System.out.println(monsterlist.isOccupied(getX()+1,getY()));

                if (getX() < 19 &&  !herolist.isOccupied(getX()+1,getY())) {
                    setPosition(getX()+1,getY());
                }
            } else {
                if (getY() < 10 && !herolist.isOccupied(getX(),getY()+1) ) {
                   setPosition(getX(),getY()+1);
                }
            }
            monsterlist.killPoistion(getX(),getY());
        }
        else {
            if (diretion == 0) {
                if (getX() > 0  && !monsterlist.isOccupied(getX()-1,getY()))
                {
                    setPosition(getX()-1,getY());
                }
            } else if (diretion == 1) {
                if (getY() > 0  && !monsterlist.isOccupied(getX(), getY() - 1))
                {
                    setPosition(getX(),getY()-1);
                }
            } else if (diretion == 2) {
                //System.out.println("start"+getX()+getY());
                //System.out.println(monsterlist.isOccupied(getX()+1,getY()));

                if (getX() < 19  && !monsterlist.isOccupied(getX() + 1, getY())) {
                    setPosition(getX()+1,getY());
                }
            } else {
                if (getY() < 10 && !monsterlist.isOccupied(getX(), getY() + 1)) {
                    setPosition(getX(),getY()+1);
                }
            }
            herolist.killPoistion(getX(),getY());

        }
    }*/

    /*protected void randomMove() {
        Random random = new Random();
        int diretion = random.nextInt(4);//0,左，1，上，2，右，3，下
        if (camp) {
            if (diretion == 0) {
                if (getX() > 0 && !herolist.isOccupied(getX()-1,getY()) && !monsterlist.isOccupied(getX()-1,getY()))
                {
                    setPosition(getX()-1,getY());
                }
            } else if (diretion == 1) {
                if (getY() > 0 && !herolist.isOccupied(getX(),getY()-1) && !monsterlist.isOccupied(getX(), getY() - 1))
                {
                    setPosition(getX(),getY()-1);
                }
            } else if (diretion == 2) {
                //System.out.println("start"+getX()+getY());
                //System.out.println(monsterlist.isOccupied(getX()+1,getY()));

                if (getX() < 19 &&  !herolist.isOccupied(getX()+1,getY()) && !monsterlist.isOccupied(getX() + 1, getY())) {
                    setPosition(getX()+1,getY());
                }
            } else {
                if (getY() < 10 && !herolist.isOccupied(getX(),getY()+1) && !monsterlist.isOccupied(getX(), getY() + 1)) {
                    setPosition(getX(),getY()+1);
                }
            }
        }
        else {
            if (diretion == 0) {
                if (getX() > 0 && !herolist.isOccupied(getX()-1,getY()) && !monsterlist.isOccupied(getX()-1,getY()))
                {
                    setPosition(getX()-1,getY());
                }
            } else if (diretion == 1) {
                if (getY() > 0 && !herolist.isOccupied(getX(),getY()-1) && !monsterlist.isOccupied(getX(), getY() - 1))
                {
                    setPosition(getX(),getY()-1);
                }
            } else if (diretion == 2) {
                //System.out.println("start"+getX()+getY());
                //System.out.println(monsterlist.isOccupied(getX()+1,getY()));

                if (getX() < 19 &&  !herolist.isOccupied(getX()+1,getY()) && !monsterlist.isOccupied(getX() + 1, getY())) {
                    setPosition(getX()+1,getY());
                }
            } else {
                if (getY() < 10 && !herolist.isOccupied(getX(),getY()+1) && !monsterlist.isOccupied(getX(), getY() + 1)) {
                    setPosition(getX(),getY()+1);
                }
            }
        }
    }*/
}
