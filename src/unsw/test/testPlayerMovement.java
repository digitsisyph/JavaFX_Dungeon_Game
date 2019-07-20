package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.entities.Boulder;
import unsw.dungeon.model.entities.Wall;
import unsw.dungeon.model.entities.door.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testPlayerMovement extends testSetup {

	@Test
	void testFreeMovement() {
		setup(3, 3, 1, 1);

		int startX = player.getX();
		int startY = player.getY();

		player.moveDown();
		assertEquals(startX, player.getX());
		assertEquals(startY + 1, player.getY());

		player.moveUp();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());

		player.moveLeft();
		assertEquals(startX - 1, player.getX());
		assertEquals(startY, player.getY());

		player.moveRight();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
	}

	@Test
	void testObstacleMovement() {
		setup(4, 4, 1, 1);

		// create four walls around the enemy
		dungeon.addEntity(new Wall(1, 0, dungeon));	// add a wall in the down
		dungeon.addEntity(new Wall(1, 2, dungeon));	// add a wall in the up
		dungeon.addEntity(new Door(0, 1, dungeon, 0));	// add a closed door in the left
		dungeon.addEntity(new Boulder(2, 1, dungeon)); // add 2 boulder in the right
		dungeon.addEntity(new Boulder(3, 1, dungeon));

		int startX = player.getX();
		int startY = player.getY();

		// player should not move
		player.moveDown();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());

		// player should not move
		player.moveUp();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());

		// player should not move
		player.moveLeft();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());

		// player should not move
		player.moveRight();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
	}



}
