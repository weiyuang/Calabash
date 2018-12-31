package gui;

import creature.Creature;
import creature.HeroList;
import creature.MonsterList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static gui.Global.*;
import static java.lang.Thread.sleep;

public class Controller {
    @FXML
    Label label;
    @FXML
    protected BorderPane borderPane;
    @FXML
    private GridPane battleground;
    @FXML
    protected MenuItem setQueue1;
    @FXML
    private BorderPane rootpane;
    @FXML
    protected void playBgm(ActionEvent event){
        System.out.println("playmusic");
        Sound sound = new Sound();
        sound.play();
    }
    @FXML
    protected void setQueue1(ActionEvent event) throws InterruptedException {
        status = 1;
        label.setText("start");
        System.out.println("hello1");
        Global.initbattleMap();
        herolist = new HeroList();
        herolist.setQueue(1);
        monsterlist = new MonsterList();
        monsterlist.setQueue(1);
        setQueue();
        paintAll();
        System.out.println("xxxxx");
        fight();
    }
    @FXML
    protected void setQueue2(ActionEvent event){
        label.setText("start");
        System.out.println("hello2");
        herolist = new HeroList();
        monsterlist = new MonsterList();
        herolist.setQueue(2);
        monsterlist.setQueue(1);
        paintAll();
        fight();
    }

    private void fight() {
        battleground.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (status != 2) {
                    for (int i = 0; i < HEIGHT; i++) {
                        for (int j = 0; j < WIDTH; j++)
                            battleMap[i][j].move();
                    }
                    paintAll();
                }
                if (checkFinish()) {
                    status = 2;
                    //System.out.println("end");
                    label.setText("end");
                }
            }
        });
    }

    private boolean checkFinish() {
        boolean heroflag = false;//if still alive
        boolean monsterflag = false;
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                if (battleMap[i][j].camp == MONSTER)
                    monsterflag = true;
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                if (battleMap[i][j].camp == HERO)
                    heroflag = true;

        if (!monsterflag || !heroflag)
            return true;
        else return false;
    }


    private void paintAll() {
        clearAll();
        round++;
        if (round == 100) {
            claerBody();
            round = 0;
        }
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                if (battleMap[i][j].camp != EMPTY) {
                    setOneImage(battleMap[i][j].getImage(), battleMap[i][j].getX(), battleMap[i][j].getY());
                }
            }
        /*for (int i=0;i < herolist.List.size();i++)
        { setOneImage(herolist.get(i).getImage(),herolist.get(i).getX(),herolist.get(i).getY());; }
        for (int i=0;i < monsterlist.List.size();i++)
        { setOneImage(monsterlist.get(i).getImage(),monsterlist.get(i).getX(),monsterlist.get(i).getY());; }*/
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(battleMap[i][j].getName() + " ");
            }
            System.out.println();
        }
    }

    private void claerBody() {
        for (int i=0;i<HEIGHT;i++)
            for (int j=0;j<WIDTH;j++)
                if (battleMap[i][j].camp==3)
                {
                    battleMap[i][j] = new Creature();
                    battleMap[i][j].setPosition(j,i);
                }
    }

    private void setOneImage(Image image, int x, int y)
    {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        battleground.add(imageView,x,y);
    }

    private void clearAll()
    {
        Node node = battleground.getChildren().get(0);
        battleground.getChildren().clear();
        battleground.getChildren().add(node);
    }

    private void setQueue(){
        for (int i=0;i<herolist.List.size();i++){
            int x = herolist.List.get(i).getY();
            int y = herolist.List.get(i).getX();
            battleMap[x][y] = herolist.List.get(i);
        }
        for (int i=0;i<monsterlist.List.size();i++){
            int x = monsterlist.List.get(i).getY();
            int y = monsterlist.List.get(i).getX();
            battleMap[x][y] = monsterlist.List.get(i);
        }
    }



}
