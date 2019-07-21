package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.Direction;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.bomb.LitBomb;
import unsw.dungeon.model.entities.bomb.UnlitBomb;
import unsw.dungeon.model.entities.door.Door;
import unsw.dungeon.model.entities.enemies.Enemy;
import unsw.dungeon.model.entities.enemies.HoundEnemy;
import unsw.dungeon.model.entities.enemies.HumanEnemy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testEntityBehaviour extends testSetup {

	/*
	 * Normally the enemy will close the distance between the player and itself
	 */
	@Test
	void testHumanEnemyMoveTowards() {
		setup(10, 1, 0, 0);

		Enemy enemy = new HumanEnemy(9, 0, dungeon);
		dungeon.addEntity(enemy);

		int distance = enemy.getX() - player.getX(); // distance should become smaller

		for (int move = 1; move < distance; move++) {
			enemy.move(player);
			assertEquals(distance - move, (enemy.getX() - player.getX()));
		}
	}

	/*
	 * Normally the enemy will close the distance between the player and itself
	 */
	@Test
	void testHoundEnemyMoveTowards() {
		setup(10, 1, 0, 0);

		Enemy enemy = new HoundEnemy(9, 0, dungeon);
		dungeon.addEntity(enemy);

		int distance = enemy.getX() - player.getX(); // distance should become smaller

		for (int move = 1; move < distance; move++) {
			enemy.move(player);
			assertEquals(distance - move, (enemy.getX() - player.getX()));
		}
	}

	/*
	 * Enemy will move away when the player is invincible
	 */
	@Test
	void testHumanEnemyMoveAway() {
		setup(10, 1, 0, 0);

		Enemy enemy = new HumanEnemy(3, 0, dungeon);
		dungeon.addEntity(enemy);

		dungeon.pickUp(new Potion(1, 0, dungeon));

		int distance = enemy.getX() - player.getX(); // distance will stay the same

		dungeon.movePlayer(Direction.RIGHT); // pick potion
		assertEquals(distance, (enemy.getX() - player.getX()));

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(distance, (enemy.getX() - player.getX()));

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(distance, (enemy.getX() - player.getX()));
	}

	/*
	 * Enemy will move away when the player is invincible
	 */
	@Test
	void testHoundEnemyMoveAway() {
		setup(10, 1, 0, 0);

		Enemy enemy = new HoundEnemy(3, 0, dungeon);
		dungeon.addEntity(enemy);

		dungeon.pickUp(new Potion(1, 0, dungeon));

		int distance = enemy.getX() - player.getX(); // distance will stay the same

		dungeon.movePlayer(Direction.RIGHT); // pick potion
		assertEquals(distance, (enemy.getX() - player.getX()));

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(distance, (enemy.getX() - player.getX()));

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(distance, (enemy.getX() - player.getX()));
	}

	/*
	 * Testing lit bomb state transition
	 */
	@Test
	void testLitBombStates() {
		setup(10, 1, 0, 0);

		dungeon.pickUp(new UnlitBomb(0, 0, dungeon));

		dungeon.placeBomb();
		LitBomb bombLit = (LitBomb) dungeon.getEntities(EntityType.LITBOMB).get(0);
		assertEquals("/bomb_lit_1.png", bombLit.getImagePath());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals("/bomb_lit_2.png", bombLit.getImagePath());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals("/bomb_lit_3.png", bombLit.getImagePath());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals("/bomb_lit_4.png", bombLit.getImagePath());
	}

	/*
	 * Exploded bombs will destroy neighbouring boulders
	 */
	@Test
	void testExplodedBombDestroyBoulders() {
		setup(10, 5, 0, 0);

		dungeon.addEntity(new Boulder(2, 1, dungeon));
		dungeon.addEntity(new Boulder(2, 3, dungeon));
		dungeon.addEntity(new Boulder(3, 2, dungeon));
		dungeon.addEntity(new Boulder(1, 2, dungeon));

		assertEquals(4, dungeon.getEntities(EntityType.BOULDER).size());

		LitBomb bomb = new LitBomb(2, 2, dungeon);
		dungeon.addEntity(bomb);
		dungeon.explodeBomb(bomb);

		assertEquals(0, dungeon.getEntities(EntityType.BOULDER).size());
	}

	/*
	 * Exploded bombs will destroy neighbouring enemies
	 */
	@Test
	void testExplodedBombDestroyEnemies() {
		setup(10, 5, 1, 2);

		dungeon.addEntity(new HumanEnemy(2, 1, dungeon));
		dungeon.addEntity(new HumanEnemy(2, 3, dungeon));
		dungeon.addEntity(new HumanEnemy(3, 2, dungeon));
		dungeon.addEntity(new HumanEnemy(1, 2, dungeon));

		assertEquals(4, dungeon.getEntities(EntityType.ENEMY).size());

		LitBomb bomb = new LitBomb(2, 2, dungeon);
		dungeon.addEntity(bomb);
		dungeon.explodeBomb(bomb);

		assertEquals(0, dungeon.getEntities(EntityType.ENEMY).size());
	}

	/*
	 * Boulder can be pushed by the player
	 */
	@Test
	void testPushBoulder() {
		setup(5, 5, 2, 2);

		Boulder boulderRight = new Boulder(3, 2, dungeon);
		Boulder boulderLeft = new Boulder(1, 2, dungeon);
		Boulder boulderUp = new Boulder(2, 1, dungeon);
		Boulder boulderDown = new Boulder(2, 3, dungeon);
		dungeon.addEntity(boulderRight);
		dungeon.addEntity(boulderLeft);
		dungeon.addEntity(boulderUp);
		dungeon.addEntity(boulderDown);

		dungeon.movePlayer(Direction.RIGHT); // pushing
		assertEquals(4, boulderRight.getX());
		assertEquals(2, boulderRight.getY());

		dungeon.movePlayer(Direction.LEFT); // pushing
		assertEquals(0, boulderLeft.getX());
		assertEquals(2, boulderLeft.getY());

		dungeon.movePlayer(Direction.UP); // pushing
		assertEquals(2, boulderUp.getX());
		assertEquals(0, boulderUp.getY());

		dungeon.movePlayer(Direction.DOWN); // pushing
		assertEquals(2, boulderDown.getX());
		assertEquals(4, boulderDown.getY());
	}

	/*
	 * Player is strong enough to only push 1 boulder at a time
	 */
	@Test
	void testCannotPushTwoBoulders() {
		setup(9, 9, 4, 4);

		Boulder boulderRight = new Boulder(5, 4, dungeon);
		Boulder boulderLeft = new Boulder(3, 4, dungeon);
		Boulder boulderUp = new Boulder(4, 3, dungeon);
		Boulder boulderDown = new Boulder(4, 5, dungeon);

		dungeon.addEntity(boulderRight);
		dungeon.addEntity(new Boulder(6, 4, dungeon));
		dungeon.addEntity(boulderLeft);
		dungeon.addEntity(new Boulder(2, 4, dungeon));
		dungeon.addEntity(boulderUp);
		dungeon.addEntity(new Boulder(4, 2, dungeon));
		dungeon.addEntity(boulderDown);
		dungeon.addEntity(new Boulder(4, 6, dungeon));

		dungeon.movePlayer(Direction.RIGHT); // pushing
		assertEquals(5, boulderRight.getX());
		assertEquals(4, boulderRight.getY());

		dungeon.movePlayer(Direction.LEFT); // pushing
		assertEquals(3, boulderLeft.getX());
		assertEquals(4, boulderLeft.getY());

		dungeon.movePlayer(Direction.UP); // pushing
		assertEquals(4, boulderUp.getX());
		assertEquals(3, boulderUp.getY());

		dungeon.movePlayer(Direction.DOWN); // pushing
		assertEquals(4, boulderDown.getX());
		assertEquals(5, boulderDown.getY());
	}

	/*
	 * Cant push boulder through wall
	 */
	@Test
	void testPushBoulderWithObstacle() {
		setup(9, 9, 4, 4);

		Boulder boulderRight = new Boulder(5, 4, dungeon);
		Boulder boulderLeft = new Boulder(3, 4, dungeon);
		Boulder boulderUp = new Boulder(4, 3, dungeon);
		Boulder boulderDown = new Boulder(4, 5, dungeon);

		dungeon.addEntity(boulderRight);
		dungeon.addEntity(new Wall(6, 4, dungeon));
		dungeon.addEntity(boulderLeft);
		dungeon.addEntity(new Wall(2, 4, dungeon));

		dungeon.addEntity(boulderUp);
		dungeon.addEntity(new Door(4, 2, dungeon, 0));
		dungeon.addEntity(boulderDown);
		dungeon.addEntity(new Door(4, 6, dungeon, 0));

		dungeon.movePlayer(Direction.RIGHT); // pushing
		assertEquals(5, boulderRight.getX());
		assertEquals(4, boulderRight.getY());

		dungeon.movePlayer(Direction.LEFT); // pushing
		assertEquals(3, boulderLeft.getX());
		assertEquals(4, boulderLeft.getY());

		dungeon.movePlayer(Direction.UP); // pushing
		assertEquals(4, boulderUp.getX());
		assertEquals(3, boulderUp.getY());

		dungeon.movePlayer(Direction.DOWN); // pushing
		assertEquals(4, boulderDown.getX());
		assertEquals(5, boulderDown.getY());
	}

	/*
	 * Player cannot push boulder into the enemy
	 */
	@Test
	void testPushBoulderEnemy() {
		setup(3, 1, 0, 0);

		Boulder boulder = new Boulder(1, 0, dungeon);
		dungeon.addEntity(boulder);
		dungeon.addEntity(new HumanEnemy(2, 0, dungeon));

		dungeon.movePlayer(Direction.RIGHT); // pushing

		assertEquals(1, boulder.getX());
		assertEquals(0, boulder.getY());
	}

	/*
	 * Player can pushed boulder through opened door
	 */
	@Test
	void testPushBoulderOpenedDoor() {
		setup(4, 1, 0, 0);
		// Adding key

		Boulder boulder = new Boulder(1, 0, dungeon);
		dungeon.addEntity(boulder);

		Door door = new Door(2, 0, dungeon, 0);
		dungeon.addEntity(door);
		dungeon.pickUp(new Key(0, 0, dungeon, 0));
		dungeon.openDoor(door);

		dungeon.movePlayer(Direction.RIGHT); // pushing
		dungeon.movePlayer(Direction.RIGHT); // boulder at door
		dungeon.movePlayer(Direction.RIGHT); // boulder passes door

		assertEquals(3, boulder.getX());
		assertEquals(0, boulder.getY());
	}
}
