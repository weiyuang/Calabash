package gui;

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
                //System.out.println("move!!!!!!");
                //herolist.move();
                //System.out.println("done");
                //monsterlist.move();
                System.out.println("moving");
                if(status!=2) {
                    for (int i = 0; i < 20; i++)
                        for (int j = 0; j < 11; j++)
                            if (battleMap[i][j].getName() != null) {
                                battleMap[i][j].move();
                            }
                    paintAll();
                }



                if (checkFinish()) {
                    status = 2;
                    System.out.println("end");
                    label.setText("end");
                }
            }
        });
    }

    private boolean checkFinish() {
        boolean heroflag = false;//if still alive
        boolean monsterflag = false;
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 11; j++)
                if (battleMap[i][j].getName() != null && battleMap[i][j].camp == false)
                    monsterflag = true;
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 11; j++)
                if (battleMap[i][j].getName() != null && battleMap[i][j].camp == true)
                    heroflag = true;

        if (!monsterflag || !heroflag)
            return true;
        else return false;
    }


    private void paintAll()
    {
        clearAll();
        for (int i=0;i<20;i++)
            for (int j=0;j<11;j++) {
                if (battleMap[i][j].getName()!=null) {
                    setOneImage(battleMap[i][j].getImage(),battleMap[i][j].getX(),battleMap[i][j].getY());;
                }
            }
        /*for (int i=0;i < herolist.List.size();i++)
        { setOneImage(herolist.get(i).getImage(),herolist.get(i).getX(),herolist.get(i).getY());; }
        for (int i=0;i < monsterlist.List.size();i++)
        { setOneImage(monsterlist.get(i).getImage(),monsterlist.get(i).getX(),monsterlist.get(i).getY());; }*/
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
            int x = herolist.List.get(i).getX();
            int y = herolist.List.get(i).getY();
            battleMap[x][y] = herolist.List.get(i);
        }
        for (int i=0;i<monsterlist.List.size();i++){
            int x = monsterlist.List.get(i).getX();
            int y = monsterlist.List.get(i).getY();
            battleMap[x][y] = monsterlist.List.get(i);
        }
    }



}
