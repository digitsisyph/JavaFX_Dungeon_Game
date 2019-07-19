package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
		setup("test-two-treasures.json");
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
		setup("test-two-keys.json");
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
		setup("test-two-bombs.json");
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
		setup("test-two-potions.json");
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
		setup("test-two-doors.json");
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
}
