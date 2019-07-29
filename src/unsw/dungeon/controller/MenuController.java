package unsw.dungeon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import unsw.dungeon.view.DungeonScreen;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button startButton;

    private DungeonScreen dungeonScreen;

    @FXML
    public void handleStartButton(ActionEvent event) {
        try {
            dungeonScreen.start("advanced.json");
        } catch (IOException e) {
            System.out.println("not existed json");
        }
    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }


}
