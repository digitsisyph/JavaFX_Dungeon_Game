package unsw.test;

import org.junit.jupiter.api.AfterEach;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Player;
import java.io.FileNotFoundException;

public class testSetup {

	DummyDungeonLoader dungeonLoader = null;
	Dungeon dungeon = null;
	Player player = null;
	
	void setup(String name) {
		try {
			dungeonLoader = new DummyDungeonLoader(name);
			dungeon = dungeonLoader.load();
			player = dungeon.getPlayer();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	@AfterEach
	void teatDown() {
		dungeonLoader = null;
		dungeon = null;
		player = null;
		System.out.println("Cleared Variables");
	}
}
