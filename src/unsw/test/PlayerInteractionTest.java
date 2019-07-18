package unsw.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import unsw.dungeon.entities.EntityType;

public class PlayerInteractionTest extends testSetup {

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
	void testPickupKey() {
		setup("test-two-keys.json");
		assertEquals(2, dungeon.getEntityOfType(EntityType.KEY).size());
		assertEquals(null, dungeon.getInventory().getKey());
		player.moveRight();
		assertNotEquals(null, dungeon.getInventory().getKey());
		assertEquals(1, dungeon.getEntityOfType(EntityType.KEY).size());
		assertEquals(99, dungeon.getInventory().getKeyID());
		player.moveRight();
		assertEquals(1, dungeon.getEntityOfType(EntityType.KEY).size());
		assertEquals(99, dungeon.getInventory().getKeyID());
	}
}
