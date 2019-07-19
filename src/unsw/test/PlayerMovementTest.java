package unsw.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerMovementTest extends testSetup {

	@Test
	void testPlayerWallCollision() {
		setup("wallAllSides.json");
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
		setup("empty.json");
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
