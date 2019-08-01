package unsw.dungeon.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class DungeonSoundPlayer {

    private static Media fightSound = new Media(new File("sounds/fight.mp3").toURI().toString());

    public static void fightSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(fightSound);
        mediaPlayer.play();
    }

}
