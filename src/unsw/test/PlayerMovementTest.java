package unsw.test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.model.entities.Wall;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerMovementTest extends testSetup {

	@Test
	void testPlayerWallCollision() {
		setup(3, 3, 1, 1);
		Wall wall = new Wall(1, 0, dungeon);
		dungeon.addEntity(wall);
		wall = new Wall(0, 1, dungeon);
		dungeon.addEntity(wall);
		wall = new Wall(1, 2, dungeon);
		dungeon.addEntity(wall);
		wall = new Wall(2, 1, dungeon);
		dungeon.addEntity(wall);
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

}
