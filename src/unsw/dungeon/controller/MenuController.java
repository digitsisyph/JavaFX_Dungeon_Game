package unsw.dungeon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import unsw.dungeon.view.DungeonScreen;

import java.io.File;
import java.io.IOException;

public class MenuController {
    @FXML
    private ChoiceBox dungeonChoice;
    @FXML
    private Button startButton;

    private DungeonScreen dungeonScreen;

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
    public void handleStartButton(ActionEvent event) {
        try {
            dungeonScreen.start(dungeonChoice.getValue().toString());
        } catch (IOException e) {
            System.out.println("not existed json");
        }
    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }


}
