package unsw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Door;
import unsw.dungeon.model.entities.Enemy;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Key;
import unsw.dungeon.model.entities.Player;
import unsw.dungeon.model.entities.Potion;
import unsw.dungeon.model.entities.Sword;
import unsw.dungeon.model.entities.Treasure;
import unsw.dungeon.model.entities.Bomb.UnlitBomb;

public class PlayerInteractionTest extends testSetup {


	// this is an example test code from the tutor
	@Test
	void testSomething() {
		Dungeon dungeon = new Dungeon(4, 5);
		Player player = new Player(2, 4, dungeon);

		dungeon.createEntity(player);

		player.moveRight();
		player.moveLeft();

		assertEquals(player.getX(), 2);
	}

	/*
	 * Player can collect any number of treasures
	 */
	@Test
	void testPickUpTreasure() {
		setup(5, 3, 1, 1);
		Treasure treasure = new Treasure(2, 1, dungeon);
		dungeon.addEntity(treasure);
		treasure = new Treasure(3, 1, dungeon);
		dungeon.addEntity(treasure);
		assertEquals(0, dungeon.getInventory().numTreasurePicked());
		player.moveRight();
		assertEquals(1, dungeon.getInventory().numTreasurePicked());
		player.moveRight();
		assertEquals(2, dungeon.getInventory().numTreasurePicked());
	}

	/*
	 * Player may only have one key at a time
	 */
	@Test
	void testPickUpKey() {
		setup(5, 3, 1, 1);
		Key key = new Key(2, 1, dungeon, 99);
		dungeon.addEntity(key);
		key = new Key(3, 1, dungeon, 20);
		dungeon.addEntity(key);
		assertEquals(2, dungeon.getEntities(EntityType.KEY).size());
		assertEquals(null, dungeon.getInventory().getKey());
		player.moveRight();
		assertNotEquals(null, dungeon.getInventory().getKey());
		assertEquals(1, dungeon.getEntities(EntityType.KEY).size());
		assertEquals(99, dungeon.getInventory().getKeyID());
		player.moveRight();
		assertEquals(1, dungeon.getEntities(EntityType.KEY).size());
		assertEquals(99, dungeon.getInventory().getKeyID());
	}

	/*
	 * Player may pick up any number of bombs
	 */
	@Test
	void testPickUpBomb() {
		setup(5, 3, 1, 1);
		UnlitBomb bomb = new UnlitBomb(2, 1, dungeon);
		dungeon.addEntity(bomb);
		bomb = new UnlitBomb(3, 1, dungeon);
		dungeon.addEntity(bomb);
		assertEquals(2, dungeon.getEntities(EntityType.UNLITBOMB).size());
		assertEquals(0, dungeon.getInventory().getBombNum());
		player.moveRight();
		assertEquals(1, dungeon.getEntities(EntityType.UNLITBOMB).size());
		assertEquals(1, dungeon.getInventory().getBombNum());
		player.moveRight();
		assertEquals(0, dungeon.getEntities(EntityType.UNLITBOMB).size());
		assertEquals(2, dungeon.getInventory().getBombNum());
	}

	/*
	 * Player picking potion will set invincibility step to 5 TODO: Should be 5?
	 */
	@Test
	void testPickUpPotion() {
		setup(5, 3, 1, 1);
		Potion potion = new Potion(2, 1, dungeon);
		dungeon.addEntity(potion);
		potion = new Potion(3, 1, dungeon);
		dungeon.addEntity(potion);
		assertEquals(2, dungeon.getEntities(EntityType.POTION).size());
		assertEquals(false, dungeon.getInventory().isInvincible());
		player.moveRight();
		assertEquals(1, dungeon.getEntities(EntityType.POTION).size());
		assertEquals(true, dungeon.getInventory().isInvincible());
		assertEquals(4, dungeon.getInventory().invincStep());
		player.moveRight();
		assertEquals(0, dungeon.getEntities(EntityType.POTION).size());
		assertEquals(true, dungeon.getInventory().isInvincible());
		assertEquals(4, dungeon.getInventory().invincStep());
	}

	@Test
	void testDoorInteraction() {
		setup(5, 3, 1, 1);
		Door door = new Door(2, 0, dungeon, 69);
		dungeon.addEntity(door);
		door = new Door(3, 0, dungeon, 99);
		dungeon.addEntity(door);
		Key key = new Key(2, 1, dungeon, 99);
		dungeon.addEntity(key);
		key = new Key(3, 1, dungeon, 69);
		dungeon.addEntity(key);
		int startX = player.getX();
		int startY = player.getY();
		int openedDoorCount = 0;
		List<Entity> doors = dungeon.getEntities(EntityType.DOOR);
		assertEquals(2, doors.size());
		assertEquals(2, dungeon.getEntities(EntityType.KEY).size());
		player.moveRight();
		assertEquals(1, dungeon.getEntities(EntityType.KEY).size());
		// should not move because wrong key
		player.moveUp();
		assertEquals(startX + 1, player.getX());
		assertEquals(startY, player.getY());
		for (Entity d : doors) {
			if (d.canPassThrough())
				openedDoorCount++;
		}
		assertEquals(0, openedDoorCount); // at this point no door unlocked
		player.moveRight(); // facing the right door
		player.moveUp(); // this action would unlock the door but player stay still
		assertEquals(null, dungeon.getInventory().getKey()); // key is used and removed
		for (Entity d : doors) {
			if (d.canPassThrough())
				openedDoorCount++;
		}
		assertEquals(1, openedDoorCount); // at this point no door unlocked
		// now the player can move up
		player.moveUp();
		assertEquals(startX + 2, player.getX());
		assertEquals(startY - 1, player.getY());

		// player to collect another key on dungeon
		player.moveDown();
		assertEquals(69, dungeon.getInventory().getKeyID()); // key picked
		player.moveLeft();
		player.moveUp();
		// reset opened door count
		openedDoorCount = 0;
		for (Entity d : doors) {
			if (d.canPassThrough())
				openedDoorCount++;
		}
		assertEquals(2, openedDoorCount);
		assertEquals(0, dungeon.getEntities(EntityType.KEY).size());
	}

	@Test
	void testPlayerSwordEnemyInteraction() {
		setup(5, 1, 0, 0);
		Enemy enemy = new Enemy(3, 0, dungeon);
		dungeon.addEntity(enemy);
		Sword sword = new Sword(1, 0, dungeon);
		dungeon.addEntity(sword);
		sword = new Sword(4, 0, dungeon);
		dungeon.addEntity(sword);
		assertEquals(2, dungeon.getEntities(EntityType.SWORD).size());
		assertEquals(1, dungeon.getEntities(EntityType.ENEMY).size());
		assertEquals(null, dungeon.getInventory().getSword());
		player.moveRight(); // on sword grid , player collect it
		assertNotEquals(null, dungeon.getInventory().getSword());
		assertEquals(5, dungeon.getInventory().getSwordDurability());
		assertEquals(1, dungeon.getEntities(EntityType.SWORD).size());
		player.moveRight(); // collide with the enemy but the player has sword
		assertEquals(0, dungeon.getEntities(EntityType.ENEMY).size());
		assertEquals(4, dungeon.getInventory().getSwordDurability());
		player.moveRight(); // move right 3 times to collect the last sword
		player.moveRight();
		player.moveRight(); // collect the last sword refresh sword's durability
		assertEquals(5, dungeon.getInventory().getSwordDurability());
		assertEquals(0, dungeon.getEntities(EntityType.SWORD).size());
	}
}
