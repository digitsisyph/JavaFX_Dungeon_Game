package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.Direction;
import unsw.dungeon.model.entities.Potion;
import unsw.dungeon.model.entities.enemies.Enemy;
import unsw.dungeon.model.entities.enemies.HoundEnemy;
import unsw.dungeon.model.entities.enemies.HumanEnemy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testEnemyBehaviour extends testSetup {

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




}
