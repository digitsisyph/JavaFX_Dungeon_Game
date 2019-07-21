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

		dungeon.addEntity(new Treasure(2, 1, dungeon));
		dungeon.addEntity(new Treasure(3, 1, dungeon));

		// the player does not have any treasure at first
		assertEquals(0, dungeon.getInventory().numTreasurePicked());
		assertEquals(2, dungeon.getEntities(EntityType.TREASURE).size());

		dungeon.movePlayerRight();
		assertEquals(1, dungeon.getInventory().numTreasurePicked());
		assertEquals(1, dungeon.getEntities(EntityType.TREASURE).size());

		dungeon.movePlayerRight();
		assertEquals(2, dungeon.getInventory().numTreasurePicked());
		assertEquals(0, dungeon.getEntities(EntityType.TREASURE).size());
	}

	/*
	 * Player may only have one key at a time
	 */
	@Test
	void testPickUpKey() {
		setup(5, 3, 1, 1);

		dungeon.addEntity(new Key(2, 1, dungeon, 99));
		dungeon.addEntity(new Key(3, 1, dungeon, 20));

		assertEquals(2, dungeon.getEntities(EntityType.KEY).size());
		assertEquals(null, dungeon.getInventory().getKey());

		dungeon.movePlayerRight();
		assertNotEquals(null, dungeon.getInventory().getKey());
		assertEquals(1, dungeon.getEntities(EntityType.KEY).size());
		assertEquals(99, dungeon.getInventory().getKeyID());

		dungeon.movePlayerRight();
		assertEquals(1, dungeon.getEntities(EntityType.KEY).size());
		assertEquals(99, dungeon.getInventory().getKeyID());
	}

	/*
	 * Player may pick up any number of bombs
	 */
	@Test
	void testPickUpBomb() {
		setup(5, 3, 1, 1);

		dungeon.addEntity(new UnlitBomb(2, 1, dungeon));
		dungeon.addEntity(new UnlitBomb(3, 1, dungeon));

		assertEquals(2, dungeon.getEntities(EntityType.UNLITBOMB).size());
		assertEquals(0, dungeon.getInventory().getBombNum());

		dungeon.movePlayerRight();
		assertEquals(1, dungeon.getEntities(EntityType.UNLITBOMB).size());
		assertEquals(1, dungeon.getInventory().getBombNum());

		dungeon.movePlayerRight();
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

		dungeon.addEntity(new Potion(2, 1, dungeon));
		dungeon.addEntity(new Potion(3, 1, dungeon));

		assertEquals(2, dungeon.getEntities(EntityType.POTION).size());
		assertEquals(false, dungeon.getInventory().isInvincible());

		dungeon.movePlayerRight();
		assertEquals(1, dungeon.getEntities(EntityType.POTION).size());
		assertEquals(true, dungeon.getInventory().isInvincible()); // at this point he becomes invincible
		assertEquals(4, dungeon.getInventory().invincStep());

		dungeon.movePlayerRight(); // collects another potion
		assertEquals(0, dungeon.getEntities(EntityType.POTION).size());
		assertEquals(true, dungeon.getInventory().isInvincible()); // at this point he becomes invincible
		assertEquals(4, dungeon.getInventory().invincStep());

		dungeon.movePlayerRight();
		assertEquals(3, dungeon.getInventory().invincStep());

		dungeon.movePlayerRight();
		assertEquals(2, dungeon.getInventory().invincStep());

		dungeon.movePlayerRight();
		assertEquals(1, dungeon.getInventory().invincStep());

		dungeon.movePlayerRight();
		assertEquals(0, dungeon.getInventory().invincStep());

		assertEquals(false, dungeon.getInventory().isInvincible()); // no longer invincible

	}

	/*
	 * Player can only use key to unlock matching door
	 */
	@Test
	void testDoorInteraction() {
		setup(5, 3, 1, 1);

		dungeon.addEntity(new Door(2, 0, dungeon, 69));
		dungeon.addEntity(new Door(3, 0, dungeon, 99));
		dungeon.addEntity(new Key(2, 1, dungeon, 99));
		dungeon.addEntity(new Key(3, 1, dungeon, 69));

		int startX = player.getX();
		int startY = player.getY();
		int openedDoorCount = 0;

		List<Entity> doors = dungeon.getEntities(EntityType.DOOR);
		assertEquals(2, doors.size());
		assertEquals(2, dungeon.getEntities(EntityType.KEY).size());

		dungeon.movePlayerRight();
		assertEquals(1, dungeon.getEntities(EntityType.KEY).size());
		// should not move because wrong key
		dungeon.movePlayerUp();
		assertEquals(startX + 1, player.getX());
		assertEquals(startY, player.getY());
		for (Entity d : doors) {
			if (d.canPassThrough())
				openedDoorCount++;
		}
		assertEquals(0, openedDoorCount); // at this point no door unlocked
		dungeon.movePlayerRight(); // facing the right door
		dungeon.movePlayerUp(); // this action would unlock the door but player stay still
		assertEquals(null, dungeon.getInventory().getKey()); // key is used and removed
		for (Entity d : doors) {
			if (d.canPassThrough())
				openedDoorCount++;
		}
		assertEquals(1, openedDoorCount); // at this point no door unlocked
		// now the player can move up
		dungeon.movePlayerUp();
		assertEquals(startX + 2, player.getX());
		assertEquals(startY - 1, player.getY());

		// player to collect another key on dungeon
		dungeon.movePlayerDown();
		assertEquals(69, dungeon.getInventory().getKeyID()); // key picked
		dungeon.movePlayerLeft();
		dungeon.movePlayerUp();
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

		dungeon.movePlayerRight(); // on sword grid , player collect it

		assertNotEquals(null, dungeon.getInventory().getSword());
		assertEquals(5, dungeon.getInventory().getSwordDurability());
		assertEquals(1, dungeon.getEntities(EntityType.SWORD).size());

		dungeon.movePlayerRight(); // collide with the enemy but the player has sword

		assertEquals(0, dungeon.getEntities(EntityType.ENEMY).size());	// the player should use the sword to kill enemy
		assertEquals(4, dungeon.getInventory().getSwordDurability());	// the durability should decrease

		dungeon.movePlayerRight(); // move right 2 times to collect the last sword
		dungeon.movePlayerRight();
		dungeon.movePlayerRight(); // collect the last sword refresh sword's durability

		assertEquals(5, dungeon.getInventory().getSwordDurability());	// the durability should be refreshed
		assertEquals(0, dungeon.getEntities(EntityType.SWORD).size());
	}

	/*
	 * When invincible the player cannot die from explosion
	 */
	@Test
	void testPlayerDieFromBomb() {
		setup(3,1,0,0);

		LitBomb bomb = new LitBomb(1, 0, dungeon);
		dungeon.addEntity(bomb);
		dungeon.explodeBomb(bomb);

		assertEquals(null, dungeon.getPlayer());
	}

	/*
	 * When invincible the player cannot die from explosion
	 */
	@Test
	void testPlayerBombInvincibility() {
		setup(2,1,0,0);

		dungeon.pickUp(new Potion(0, 0, dungeon));

		LitBomb bomb = new LitBomb(1, 0, dungeon);
		dungeon.addEntity(bomb);
		dungeon.explodeBomb(bomb);

		assertNotEquals(null, dungeon.getPlayer());
	}

}
