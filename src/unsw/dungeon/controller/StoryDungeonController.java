package unsw.dungeon.controller;

import javafx.scene.image.ImageView;
import unsw.dungeon.loader.DungeonControllerLoader;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.view.DungeonScreen;

import java.util.List;

public class StoryDungeonController extends DungeonController {

	private DungeonScreen currDungeonScreen;
	private DungeonScreen nextDungeonScreen;
	private DungeonScreen prevDungeonScreen;

	public StoryDungeonController(DungeonScreen screen) {
		super(screen);
	}

	public StoryDungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader loader) {
		super(dungeon, initialEntities, loader);
	}

	public void setNextDungeonScreen(DungeonScreen nextDungeonScreen) {
		this.nextDungeonScreen = nextDungeonScreen;
	}

	public void setPrevDungeonScreen(DungeonScreen prevDungeonScreen) {
		this.prevDungeonScreen = prevDungeonScreen;
	}

	public void switchNextDungeon() {
		getTimeline().stop();
		this.nextDungeonScreen.start(getDungeon());
	}

	public void switchPrevDungeon() {
		getTimeline().stop();
		this.prevDungeonScreen.start(getDungeon());
	}

	@Override
	public void restart() {
		getMenuScreen().getController().startStoryDungeonScreen();
	}
}
