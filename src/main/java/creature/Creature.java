package creature;

import javafx.scene.image.Image;

import java.util.Random;

import static gui.Global.*;


public class Creature {
    protected String name;
    Image image;
    public int camp;
    protected boolean state;//0：dead  1：alive
    public double power;

    public Creature() {
        name = null;
        camp = EMPTY;
        state = false;
        power = 0.5;
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

    public void move() {
        if (this.camp == MONSTER || this.camp == HERO) {
            Random random = new Random();
            int desX = getX(), desY = getY();
            int diretion = random.nextInt(4);
            if (diretion == 0) {
                desX--;
            } else if (diretion == 1) {
                desY--;
            } else if (diretion == 2) {
                desX++;
            } else {
                desY++;
            }
            if (desX < WIDTH && desX > 0 && desY < HEIGHT && desY > 0) {
                int preX=getX();
                int preY=getY();
                //System.out.println("last position" + getX() + " "+ getY());
                //System.out.println("dest position" + desX+" "+desY);
                if (this.camp == HERO) {
                    //System.out.println(this.name + "hero want move");
                    if (battleMap[desY][desX].camp == EMPTY) {
                        //System.out.println("hero move");
                        this.setPosition(desX, desY);
                        //System.out.print(battleMap[getY()][getX()].name + "放置到");
                        battleMap[desY][desX] = this;
                        //System.out.println(battleMap[desY][desX].name +desX+desY+ " OK");
                        battleMap[preY][preX] = new Creature();
                        battleMap[preY][preX].setPosition(getX(),getY());
                        //System.out.println(battleMap[desY][desX].name +desX + desY + "是现在的位置");
                    } else if (battleMap[desY][desX].camp == MONSTER) {
                        //System.out.println("hero fight");
                        battleMap[preY][preX].power+=0.05;
                        battleMap[desY][desX] = new Body(desX, desY);
                        battleMap[desY][desX].setPosition(desX,desY);
                        //System.out.println(desX+" "+desY+" "+battleMap[desX][desY].getX()+" "+battleMap[desX][desY].getY());
                    }
                }
                else if (this.camp == MONSTER)
                {
                    if (battleMap[desY][desX].camp == EMPTY) {
                        setPosition(desX, desY);
                        battleMap[desY][desX] = this;
                        battleMap[preY][preX] = new Creature();
                        battleMap[preY][preX].setPosition(getX(),getY());
                    } else if (battleMap[desY][desX].camp == HERO) {
                        battleMap[preY][preX].power+=0.05;
                        battleMap[desY][desX] = new Body(desX, desY);
                        battleMap[desY][desX].setPosition(desX,desY);
                    }
                }
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
