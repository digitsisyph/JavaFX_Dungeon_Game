package unsw.dungeon;

import javafx.application.Application;
import javafx.stage.Stage;
import unsw.dungeon.loader.DungeonLevelLoader;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.MenuScreen;

import java.io.IOException;

public class DungeonApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		// create the menu screen and dungeon screen
		MenuScreen menuScreen = new MenuScreen(primaryStage);
		DungeonScreen freeDungeonScreen = new DungeonScreen(primaryStage);

		// create the storyDungeon screen
		String[] levels_json = new String[] {
				"intro.json",
				"level1.json",
				"level6.json",
				"level7.json",
				"level8.json",
		};
		DungeonScreen storyDungeonScreen = DungeonLevelLoader.loadLevels(levels_json, primaryStage, menuScreen);

		// link the menu and dungeon screen
		freeDungeonScreen.getController().setMenuScreen(menuScreen);
		menuScreen.getController().setFreeDungeonScreen(freeDungeonScreen);
		menuScreen.getController().setStoryDungeonScreen(storyDungeonScreen);


		menuScreen.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
