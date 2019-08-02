package unsw.dungeon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
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
    private Text warning;

    private DungeonScreen freeDungeonScreen;
    private DungeonScreen storyDungeonScreen;

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
        try {
            freeDungeonScreen.load(dungeonChoice.getValue().toString());
            warning.setText("");
            freeDungeonScreen.start();
        } catch (Exception e) {
            warning.setText("Choose one");
        }
    }

    @FXML
    public void handleStoryStartButton(ActionEvent event) {
        storyDungeonScreen.start();
    }

    public void setFreeDungeonScreen(DungeonScreen dungeonScreen) {
        this.freeDungeonScreen = dungeonScreen;
    }

    public void setStoryDungeonScreen(DungeonScreen dungeonScreen) {
        this.storyDungeonScreen = dungeonScreen;
    }


}
