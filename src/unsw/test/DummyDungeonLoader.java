package unsw.test;

import java.io.FileNotFoundException;

import unsw.dungeon.DungeonLoader;
import unsw.dungeon.entities.Entity;

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
