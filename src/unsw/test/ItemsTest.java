package unsw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.model.entities.Enemy;
import unsw.dungeon.model.entities.Potion;

public class ItemsTest extends testSetup {

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
	
	@Test
	void testEnemyMoveTowards() {
		setup(10, 1, 0, 0);
		Enemy enemy = new Enemy(9, 0, dungeon);
		dungeon.addEntity(enemy);
		int distance = enemy.getX() - player.getX(); // distance will stay the same
		player.moveRight(); // pick potion
		assertEquals(distance - 2, (enemy.getX() - player.getX()));
		player.moveRight();
		assertEquals(distance - 4 , (enemy.getX() - player.getX()));
		player.moveRight();
		assertEquals(distance - 6 , (enemy.getX() - player.getX()));
	}
	
	@Test
	void testLitBomb() {
		
	}
	
	@Test
	void testExplodedBombDestroyEntities() {
		
	}
}
