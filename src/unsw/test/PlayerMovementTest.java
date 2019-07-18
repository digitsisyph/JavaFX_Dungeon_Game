package unsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.movable.Player;

class PlayerMovementTest {
	private String allWalls = "wallAllSides.json";
	private String nothing = "empty.json";
	DummyDungeonLoader dungeonLoader = null;
	Dungeon dungeon = null;
	Player player = null;
	
	void setup(String name) throws FileNotFoundException {
		dungeonLoader = new DummyDungeonLoader(name);
		dungeon = dungeonLoader.load();
		player = dungeon.getPlayer();
	}
	@AfterEach
	void teatDown() {
		dungeonLoader = null;
		dungeon = null;
		player = null;
	}
	@Test
	void testPlayerWallCollision() throws FileNotFoundException{
		setup(allWalls);
		int startX = player.getX();
		int startY = player.getY();
		player.moveDown();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
		player.moveUp();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
		player.moveLeft();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
		player.moveRight();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
	}
	
	@Test
	void testFreeMovement() throws FileNotFoundException {
		setup(nothing);
		int startX = player.getX();
		int startY = player.getY();
		player.moveDown();
		assertEquals(startX, player.getX());
		assertEquals(startY+1, player.getY());
		player.moveUp();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
		player.moveLeft();
		assertEquals(startX-1, player.getX());
		assertEquals(startY, player.getY());
		player.moveRight();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
	}

}
