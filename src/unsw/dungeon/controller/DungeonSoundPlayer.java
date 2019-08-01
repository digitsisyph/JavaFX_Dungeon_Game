package unsw.dungeon.controller;

import javafx.scene.media.AudioClip;
import java.io.File;

public class DungeonSoundPlayer {

    private static AudioClip fightSound = new AudioClip(new File("sounds/fight.mp3").toURI().toString());
    private static AudioClip potionSound = new AudioClip(new File("sounds/potion.mp3").toURI().toString());
    private static AudioClip doorSound = new AudioClip(new File("sounds/open_door.mp3").toURI().toString());
    private static AudioClip achieveItemSound = new AudioClip(new File("sounds/achieve_item.mp3").toURI().toString());
    private static AudioClip switchFloorSound = new AudioClip(new File("sounds/switch_floor.mp3").toURI().toString());
    private static AudioClip explodeSound = new AudioClip(new File("sounds/explosion.mp3").toURI().toString());

    public static void fightSound() {
        fightSound.play();
    }

    public static void potionSound() {
        potionSound.play();
    }

    public static void doorSound() {
        doorSound.play();
    }

    public static void achieveItemSound() {
        achieveItemSound.play();
    }

    public static void switchFloorSound() {
        switchFloorSound.play();
    }

    public static void explodeSound() {
        explodeSound.play();
    }
}