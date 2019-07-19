package unsw.dungeon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.DungeonController;

import java.io.IOException;

public class DungeonApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {


		// use ControllerLoader to load map from a json file
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("test-two-bombs.json");
		// create a Controller from the ControllerLoader
		DungeonController controller = dungeonLoader.loadController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);

		// get the root and set scene
		Parent root = loader.load();
		Scene scene = new Scene(root);
		root.requestFocus();

		// set primary stage
		primaryStage.setTitle("Dungeon");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
