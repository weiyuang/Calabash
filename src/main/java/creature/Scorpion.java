package creature;

import javafx.scene.image.Image;

import java.net.URL;

import static gui.Global.MONSTER;

public class Scorpion extends Creature{
    public Scorpion(){
        this.name = "蝎子精";
        URL url = getClass().getResource("/image/scorpion.jpg");
        this.image = new Image(url.toString());
        this.camp=MONSTER;
        this.power = 0.6;
    }
}
