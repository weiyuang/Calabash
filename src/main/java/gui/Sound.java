package gui;

import javafx.scene.media.AudioClip;
import javax.swing.*;
import java.net.URL;

public class Sound extends JApplet {

    void play() {
        URL url = getClass().getResource("/music/bgm.mp3");
        AudioClip audioClip = new AudioClip(url.toExternalForm());
        audioClip.play();
    }
}