package unsw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.model.entities.Enemy;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Potion;
import unsw.dungeon.model.entities.Bomb.LitBomb;
import unsw.dungeon.model.entities.Bomb.UnlitBomb;

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
		assertEquals(distance - 4, (enemy.getX() - player.getX()));
		player.moveRight();
		assertEquals(distance - 6, (enemy.getX() - player.getX()));
	}

	@Test
	void testLitBombStates() {
		setup(10, 1, 0, 0);
		UnlitBomb bomb = new UnlitBomb(1, 0, dungeon);
		dungeon.addEntity(bomb);
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		//player.placeBomb();
		LitBomb bombLit = (LitBomb) dungeon.getEntities(EntityType.LITBOMB).get(0);
		assertEquals("/bomb_lit_1.png", bombLit.getImagePath());
		player.moveLeft();
		assertEquals("/bomb_lit_2.png", bombLit.getImagePath());
		player.moveLeft();
		assertEquals("/bomb_lit_3.png", bombLit.getImagePath());
		player.moveLeft();
		assertEquals("/bomb_lit_4.png", bombLit.getImagePath());
	}

	@Test
	void testExplodedBombDestroyEntities() {

	}
}
