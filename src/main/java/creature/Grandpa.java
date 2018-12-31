package creature;

import javafx.scene.image.Image;

import java.net.URL;

import static gui.Global.HERO;

public class Grandpa extends Creature {
    public Grandpa(){
        this.name = "爷爷";
        URL url = getClass().getResource("/image/grandpa.jpg");
        this.image = new Image(url.toString());
        this.camp=HERO;
    }
}
