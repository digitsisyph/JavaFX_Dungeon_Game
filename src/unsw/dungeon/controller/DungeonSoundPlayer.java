package unsw.dungeon.controller;

import javafx.scene.media.AudioClip;
import java.io.File;

class DungeonSoundPlayer {

    private static AudioClip fightSound = new AudioClip(new File("sounds/fight.mp3").toURI().toString());
    private static AudioClip potionSound = new AudioClip(new File("sounds/potion.mp3").toURI().toString());
    private static AudioClip doorSound = new AudioClip(new File("sounds/open_door.mp3").toURI().toString());
    private static AudioClip achieveItemSound = new AudioClip(new File("sounds/achieve_item.mp3").toURI().toString());
    private static AudioClip switchFloorSound = new AudioClip(new File("sounds/switch_floor.mp3").toURI().toString());
    private static AudioClip explodeSound = new AudioClip(new File("sounds/explosion.mp3").toURI().toString());
    private static AudioClip gameOverSound = new AudioClip(new File("sounds/gameover.mp3").toURI().toString());
    private static AudioClip BGM = new AudioClip(new File("sounds/bg.mp3").toURI().toString());


    static void fightSound() {
        fightSound.play();
    }

    static void potionSound() {
        potionSound.play();
    }

    static void doorSound() {
        doorSound.play();
    }

    static void achieveItemSound() {
        achieveItemSound.play();
    }

    static void switchFloorSound() {
        switchFloorSound.play();
    }

    static void explodeSound() {
        explodeSound.play();
    }

    static void gameOverSound() {
        gameOverSound.play();
    }

    static void playBGM() {
        BGM.setCycleCount(AudioClip.INDEFINITE);
        BGM.stop();
        BGM.play();
    }

    static void stopBGM() {
        BGM.stop();
    }
}