package unsw.dungeon.controller;

import javafx.scene.image.ImageView;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.MenuScreen;

import java.util.List;

public class FreeDungeonController extends DungeonController {

	private MenuScreen menuScreen;
	private DungeonScreen currDungeonScreen;

	public FreeDungeonController(DungeonScreen screen) {
		super(screen);
	}

	public FreeDungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader loader) {
		super(dungeon, initialEntities, loader);
	}

	@Override
	public void restart() {
		getCurrDungeonScreen().restart();
	}
}
