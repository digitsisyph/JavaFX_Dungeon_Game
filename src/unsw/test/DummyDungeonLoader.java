package unsw.test;

import unsw.dungeon.DungeonLoader;
import unsw.dungeon.model.entities.Entity;

import java.io.FileNotFoundException;

public class DummyDungeonLoader extends DungeonLoader {

	public DummyDungeonLoader(String filename) throws FileNotFoundException {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onLoad(Entity entity) {
		// does nothing
	}

}
