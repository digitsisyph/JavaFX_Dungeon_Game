package unsw.dungeon;

import javafx.application.Application;
import javafx.stage.Stage;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.MenuScreen;

import java.io.IOException;

public class DungeonApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		//// use ControllerLoader to load map from a json file
		//DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
		//// create a Controller from the ControllerLoader
		//DungeonController controller = dungeonLoader.loadController();

		//FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		//loader.setController(controller);

		//// get the root and set scene
		//Parent root = loader.load();
		//Scene scene = new Scene(root);
		//root.requestFocus();

		//// set primary stage
		//primaryStage.setTitle("Dungeon");
		//primaryStage.setScene(scene);
		//primaryStage.show();

		MenuScreen menuScreen = new MenuScreen(primaryStage);
		DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
		dungeonScreen.getController().setMenuScreen(menuScreen);
		menuScreen.getController().setDungeonScreen(dungeonScreen);

		menuScreen.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
