package unsw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.model.entities.Boulder;
import unsw.dungeon.model.entities.Door;
import unsw.dungeon.model.entities.Enemy;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Key;
import unsw.dungeon.model.entities.Potion;
import unsw.dungeon.model.entities.Wall;
import unsw.dungeon.model.entities.Bomb.LitBomb;
import unsw.dungeon.model.entities.Bomb.UnlitBomb;

public class testEntityBehaviour extends testSetup {

	/*
	 * Enemy will move away when the player is invincible
	 */
	@Test
	void testEnemyMoveAway() {
		setup(10, 1, 0, 0);
		Potion potion = new Potion(1, 0, dungeon);
		Enemy enemy = new Enemy(3, 0, dungeon);
		dungeon.addEntity(enemy);
		dungeon.addEntity(potion);
		int distance = enemy.getX() - player.getX(); // distance will stay the same
		player.moveRight(); // pick potion
		assertEquals(distance, (enemy.getX() - player.getX()));
		player.moveRight();
		assertEquals(distance, (enemy.getX() - player.getX()));
		player.moveRight();
		assertEquals(distance, (enemy.getX() - player.getX()));
	}
	/*
	 * Normally the enemy will close the distance between the player
	 * and itself
	 */
	@Test
	void testEnemyMoveTowards() {
		setup(10, 1, 0, 0);
		Enemy enemy = new Enemy(9, 0, dungeon);
		dungeon.addEntity(enemy);
		int distance = enemy.getX() - player.getX(); // distance will stay the same
		player.moveRight(); // pick potion
		assertEquals(distance - 2, (enemy.getX() - player.getX()));
		player.moveRight();
		assertEquals(distance - 4, (enemy.getX() - player.getX()));
		player.moveRight();
		assertEquals(distance - 6, (enemy.getX() - player.getX()));
	}
	/*
	 * Testing lit bomb state transition
	 */
	@Test
	void testLitBombStates() {
		setup(10, 1, 0, 0);
		UnlitBomb bomb = new UnlitBomb(1, 0, dungeon);
		dungeon.addEntity(bomb);
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		dungeon.playerPlacesBomb();
		LitBomb bombLit = (LitBomb) dungeon.getEntities(EntityType.LITBOMB).get(0);
		assertEquals("/bomb_lit_1.png", bombLit.getImagePath());
		player.moveLeft();
		assertEquals("/bomb_lit_2.png", bombLit.getImagePath());
		player.moveLeft();
		assertEquals("/bomb_lit_3.png", bombLit.getImagePath());
		player.moveLeft();
		assertEquals("/bomb_lit_4.png", bombLit.getImagePath());
	}

	/*
	 * Exploded bombs will destroy neighbouring boulders
	 */
	@Test
	void testExplodedBombDestroyBoulders() {
		setup(10, 5, 1, 2);
		UnlitBomb bomb = new UnlitBomb(2, 2, dungeon);
		Boulder b = new Boulder(2, 1, dungeon);
		dungeon.addEntity(b);
		b = new Boulder(3, 2, dungeon);
		dungeon.addEntity(b);
		b = new Boulder(2, 3, dungeon);
		dungeon.addEntity(b);
		dungeon.addEntity(bomb);
		player.moveRight(); // at 2 2
		dungeon.playerPlacesBomb();
		assertEquals(3, dungeon.getEntities(EntityType.BOULDER).size());
		player.moveLeft();
		player.moveLeft();
		player.moveLeft();
		assertEquals(0, dungeon.getEntities(EntityType.BOULDER).size());
	}

	/*
	 * Exploded bombs will destroy neighbouring enemies
	 */
	@Test
	void testExplodedBombDestroyEnemies() {
		setup(10, 5, 1, 2);
		UnlitBomb bomb = new UnlitBomb(2, 2, dungeon);
		Boulder b = new Boulder(2, 1, dungeon);
		dungeon.addEntity(b);
		b = new Boulder(3, 2, dungeon);
		dungeon.addEntity(b);
		b = new Boulder(2, 3, dungeon);
		dungeon.addEntity(b);
		dungeon.addEntity(bomb);
		player.moveRight(); // at 2 2
		dungeon.playerPlacesBomb();
		assertEquals(3, dungeon.getEntities(EntityType.BOULDER).size());
		player.moveLeft();
		player.moveLeft();
		player.moveLeft();
		player.moveLeft();
		assertEquals(0, dungeon.getEntities(EntityType.BOULDER).size());
	}

	/*
	 * Boulder can be pushed by the player
	 */
	@Test
	void testPushBoulder() {
		setup(3, 1, 0, 0);
		Boulder bou = new Boulder(1, 0, dungeon);
		dungeon.addEntity(bou);
		assertEquals(0, dungeon.getEntities(2, 0).size());
		player.moveRight(); // pushing
		assertEquals(1, dungeon.getEntities(2, 0).size());
		assertTrue(dungeon.getEntities(2, 0).get(0) instanceof Boulder);
	}
	/*
	 * Player is strong enough to only push 1 boulder at a time
	 */
	@Test
	void testCannotPushTwoBoulders() {
		setup(4, 1, 0, 0);
		Boulder bou = new Boulder(1, 0, dungeon);
		dungeon.addEntity(bou);
		bou = new Boulder(2, 0, dungeon);
		dungeon.addEntity(bou);
		player.moveRight(); // pushing
		assertEquals(0, dungeon.getEntities(3, 0).size()); // no boulder on x3y0
	}
	/*
	 * Cant push boulder through wall
	 */
	@Test
	void testPushBoulderWall() {
		setup(3, 1, 0, 0);
		Boulder bou = new Boulder(1, 0, dungeon);
		dungeon.addEntity(bou);
		Wall wall = new Wall(2, 0, dungeon);
		dungeon.addEntity(wall);
		player.moveRight(); // pushing
		assertTrue(dungeon.getEntities(1, 0).get(0) instanceof Boulder);
	}
	/*
	 * Cant push boulder through closed door
	 */
	@Test
	void testPushBoulderClosedDoor() {
		setup(3, 1, 0, 0);
		Boulder bou = new Boulder(1, 0, dungeon);
		dungeon.addEntity(bou);
		Door door = new Door(2, 0, dungeon, 0);
		dungeon.addEntity(door);
		player.moveRight(); // pushing
		assertTrue(dungeon.getEntities(1, 0).get(0) instanceof Boulder);
	}
	/*
	 * Player cannot push boulder into the enemy
	 */
	@Test
	void testPushBoulderEnemy() {
		setup(3, 1, 0, 0);
		Boulder bou = new Boulder(1, 0, dungeon);
		dungeon.addEntity(bou);
		Enemy enemy = new Enemy(2, 0, dungeon);
		dungeon.addEntity(enemy);
		player.moveRight(); // pushing
		assertTrue(dungeon.getEntities(1, 0).get(0) instanceof Boulder);
	}
	/*
	 * Player can pushed boulder through opened door
	 */
	@Test
	void testPushBoulderOpenedDoor() {
		setup(4, 1, 0, 0);
		// Adding key
		Key key = new Key(0, 0, dungeon, 0);
		dungeon.addEntity(key);
		Boulder bou = new Boulder(1, 0, dungeon);
		dungeon.addEntity(bou);
		Door door = new Door(2, 0, dungeon, 0);
		dungeon.addEntity(door);
		dungeon.pickUp(key);
		dungeon.attemptToOpenDoor(door);
		player.moveRight(); // pushing
		player.moveRight(); // boulder at door
		player.moveRight(); // boulder passes door
		assertTrue(dungeon.getEntities(3, 0).get(0) instanceof Boulder);
	}
}
