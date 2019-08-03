package unsw.dungeon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.loader.DungeonScreenLoader;
import unsw.dungeon.view.DungeonScreen;

import java.io.File;

public class MenuController {

    @FXML
    private ChoiceBox<String> dungeonChoice;
    @FXML
    private Button freeStartButton;
    @FXML
    private Button storyStartButton;

    @FXML
    public void initialize() {
        // initialize the dungeon list
        for (File file : readDungeons())
            dungeonChoice.getItems().add(file.getName());
    }

    private File[] readDungeons() {
        File folder = new File("dungeons/");
        return folder.listFiles();
    }

    @FXML
    public void handleFreeStartButton(ActionEvent event) {
        startFreeDungeonScreen();
    }

    @FXML
    public void handleStoryStartButton(ActionEvent event) {
        startStoryDungeonScreen();
    }

    public void startFreeDungeonScreen() {
        DungeonScreen freeScreen = DungeonScreenLoader.loadFreeScreen(dungeonChoice.getValue().toString());
        if (freeScreen != null)
            freeScreen.start();
    }

    public void startStoryDungeonScreen() {
        DungeonScreen storyScreen = DungeonScreenLoader.loadStoryScreen(DungeonApplication.getLevelsJson());
        if (storyScreen != null)
            storyScreen.start();
    }

}
