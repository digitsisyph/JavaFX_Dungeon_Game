package unsw.dungeon.loader;

import javafx.stage.Stage;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.MenuScreen;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class DungeonLevelLoader {

	public static DungeonScreen loadLevels(String[] levels_json, Stage primaryStage, MenuScreen menuScreen) {
		List<DungeonScreen> levels = new LinkedList<DungeonScreen>();
		for (String level_json : levels_json) {
			DungeonScreen curr_screen = new DungeonScreen(primaryStage);
			curr_screen.getController().setMenuScreen(menuScreen);
			try {
				curr_screen.load(level_json);
			} catch (IOException e) {
				System.out.println("The level json does not exist!");
			}
			levels.add(curr_screen);

			if (levels.size() == 1) 	continue;
			DungeonScreen prev_screen = levels.get(levels.size() - 2);
			prev_screen.getController().setNextDungeonScreen(curr_screen);
			curr_screen.getController().setPrevDungeonScreen(prev_screen);
		}

		if (levels.size() > 0)
			return levels.get(0);
		else
			return null;
	}

}
