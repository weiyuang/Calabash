package creature;

import javafx.scene.image.Image;

import java.net.URL;

import static gui.Global.MONSTER;

public class Snake extends Creature {
    public Snake(){
        this.name = "蛇精";
        URL url = getClass().getResource("/image/snake.jpg");
        this.image = new Image(url.toString());
        this.camp=MONSTER;
    }
}
