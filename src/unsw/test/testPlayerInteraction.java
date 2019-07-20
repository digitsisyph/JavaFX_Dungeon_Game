package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.bomb.LitBomb;
import unsw.dungeon.model.entities.bomb.UnlitBomb;
import unsw.dungeon.model.entities.door.Door;
import unsw.dungeon.model.entities.enemies.HumanEnemy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class testPlayerInteraction extends testSetup {

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
	 * Player picking potion will set invincibility step to 5. Player is invincible
	 * for 5 steps counting the first step from the moment he steps into the grid
	 * containing the potion
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
		assertEquals(true, dungeon.getInventory().isInvincible()); // at this point he becomes invincible
		assertEquals(4, dungeon.getInventory().invincStep());
		player.moveRight(); // collects another potion
		assertEquals(0, dungeon.getEntities(EntityType.POTION).size());
		assertEquals(true, dungeon.getInventory().isInvincible()); // at this point he becomes invincible
		assertEquals(4, dungeon.getInventory().invincStep());
		player.moveRight();
		assertEquals(3, dungeon.getInventory().invincStep());
		player.moveRight();
		assertEquals(2, dungeon.getInventory().invincStep());
		player.moveRight();
		assertEquals(1, dungeon.getInventory().invincStep());
		player.moveRight();
		assertEquals(0, dungeon.getInventory().invincStep());
		assertEquals(false, dungeon.getInventory().isInvincible()); // no longer invincible

	}

	/*
	 * Player can only use key to unlock matching door
	 */
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

	/*
	 * Player can use sword to kill the enemy Each time a sword is used its
	 * durability is decreased by one.
	 */
	@Test
	void testPlayerSwordEnemyInteraction() {

		// initialize a 3 * 1 dungeon, and add an enemy and two swords into it
		setup(4, 1, 0, 0);
		dungeon.addEntity(new Sword(1, 0, dungeon));
		dungeon.addEntity(new HumanEnemy(3, 0, dungeon));
		dungeon.addEntity(new Sword(4, 0, dungeon));


		assertEquals(2, dungeon.getEntities(EntityType.SWORD).size());
		assertEquals(1, dungeon.getEntities(EntityType.ENEMY).size());
		assertEquals(null, dungeon.getInventory().getSword());

		player.moveRight(); // on sword grid , player collect it

		assertNotEquals(null, dungeon.getInventory().getSword());
		assertEquals(5, dungeon.getInventory().getSwordDurability());
		assertEquals(1, dungeon.getEntities(EntityType.SWORD).size());

		player.moveRight(); // collide with the enemy but the player has sword

		assertEquals(0, dungeon.getEntities(EntityType.ENEMY).size());	// the player should use the sword to kill enemy
		assertEquals(4, dungeon.getInventory().getSwordDurability());	// the durability should decrease

		player.moveRight(); // move right 2 times to collect the last sword
		player.moveRight();
		player.moveRight(); // collect the last sword refresh sword's durability

		assertEquals(5, dungeon.getInventory().getSwordDurability());	// the durability should be refreshed
		assertEquals(0, dungeon.getEntities(EntityType.SWORD).size());
	}

	/*
	 * When invincible the player cannot die from explosion
	 */
	@Test
	void testPlayerBombInvincibility() {
		setup(3,1,0,0);
		LitBomb bomb = new LitBomb(2, 0, dungeon);
		Potion potion = new Potion(1,0, dungeon);
		dungeon.addEntity(potion);
		dungeon.addEntity(bomb);
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		assertNotEquals(null,dungeon.getPlayer());
	}
	/*
	 * When invincible the player cannot die from explosion
	 */
	@Test
	void testPlayerDieFromBomb() {
		setup(3,1,0,0);
		LitBomb bomb = new LitBomb(2, 0, dungeon);
		dungeon.addEntity(bomb);
		player.moveRight();
		player.moveRight();
		player.moveRight();
		assertEquals(null,dungeon.getPlayer());
	}
}
