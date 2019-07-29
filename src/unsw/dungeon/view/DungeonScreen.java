package unsw.dungeon.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.controller.DungeonController;

import java.io.IOException;

public class DungeonScreen {

    private Stage stage;
    private String title = "Dungeon Game";
    private DungeonController controller;
    private Scene scene;

    public DungeonScreen(Stage stage) {
        this.stage = stage;
        this.controller = new DungeonController();
    }

    public void start(String mapJson) throws IOException{
        // use ControllerLoader to load map from a json file
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(mapJson);

        // create a Controller from the ControllerLoader
        dungeonLoader.loadController(this.controller);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(this.controller);

        // get the root and set scene
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public DungeonController getController() {
        return controller;
    }

}
