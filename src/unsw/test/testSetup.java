package unsw.test;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.movable.Player;

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
