package creature;

import javafx.scene.image.Image;

import java.net.URL;

import static gui.Global.BODY;
import static gui.Global.battleMap;

public class Body extends Creature{
    Body(){
        this.name = "尸体";
        URL url = getClass().getResource("/image/dead.jpg");
        this.image = new Image(url.toString());
        this.state= false;
        this.camp = BODY;
    }
    Body(int x, int y){
        this.name = "尸体";
        URL url = getClass().getResource("/image/dead.jpg");
        this.image = new Image(url.toString());
        this.position.setPosition(x,y);
        this.state = false;
        this.camp = BODY;
    }

}
