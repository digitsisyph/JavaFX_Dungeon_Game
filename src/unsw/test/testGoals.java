package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.enemies.HumanEnemy;
import unsw.dungeon.model.goal.*;

import static org.junit.jupiter.api.Assertions.*;

public class testGoals extends testSetup {

	/*
	 * Collect all treasures to complete the goal
	 */
	@Test
	void testTreasureGoal() {
		setup(5, 3, 1, 1);
		baseGoal.add(new TreasureGoal(dungeon));

		dungeon.addEntity(new Treasure(2, 1, dungeon));
		dungeon.addEntity(new Treasure(3, 1, dungeon));

		player.moveRight();
		assertEquals(false, dungeon.goalAchieved());

		player.moveRight();
		assertEquals(true, dungeon.goalAchieved());
	}

	/*
	 * Kill all enemies to complete the goal
	 */
	@Test
	void testEnemyGoal() {
		setup(5, 1, 0, 0);
		baseGoal.add(new EnemyGoal(dungeon));
		dungeon.addEntity(new HumanEnemy(1, 0, dungeon));

		Sword sword = new Sword(0, 0, dungeon);
		dungeon.addEntity(sword);
		dungeon.pickUp(sword);

		assertEquals(false, dungeon.goalAchieved());
		player.moveRight(); // pick sword
		assertEquals(true, dungeon.goalAchieved());

	}

	/*
	 * Push all boulders on switches
	 */
	@Test
	void testBouldersGoal() {
		setup(3, 1, 0, 0);

		baseGoal.add(new SwitchGoal(dungeon));

		dungeon.addEntity(new Switch(1, 0, dungeon));
		dungeon.addEntity(new Switch(2, 0, dungeon));

		assertFalse(dungeon.goalAchieved());

		// put boulders on switches
		dungeon.addEntity(new Boulder(1, 0, dungeon));
		dungeon.addEntity(new Boulder(2, 0, dungeon));

		assertTrue(dungeon.goalAchieved());
	}

	/*
	 * Be at the exit to complete the goal
	 */
	@Test
	void testExitGoal() {
		setup(5, 5, 2, 2);

		baseGoal.add(new ExitGoal(dungeon));

		dungeon.addEntity(new Exit(2, 1, dungeon));

		assertFalse(dungeon.goalAchieved());

		player.moveUp();	// let player move to the exit
		assertTrue(dungeon.goalAchieved());
	}

	/*
	 * Testing 2 AND goals
	 */
	@Test
	void testOneLevelCompositeGoal() {
		setup(3, 1, 0, 0);

		AndGoals andGoals = new AndGoals();
		andGoals.add(new ExitGoal(dungeon));
		andGoals.add(new TreasureGoal(dungeon));
		baseGoal.add(andGoals);

		dungeon.addEntity(new Treasure(1, 0, dungeon));
		dungeon.addEntity(new Exit(2, 0, dungeon));

		player.moveRight();
		assertFalse(dungeon.goalAchieved());

		player.moveRight();
		assertTrue(dungeon.goalAchieved());
	}

	/*
	 * Testing two level goals AND + (OR goals)
	 */
	@Test
	void testTwoLevelCompositeGoal1() {
		setup(5, 3, 2, 2);

		AndGoals andGoals = new AndGoals();
		andGoals.add(new ExitGoal(dungeon));
		OrGoals orGoals = new OrGoals();
		andGoals.add(orGoals);
		orGoals.add(new SwitchGoal(dungeon));
		orGoals.add(new TreasureGoal(dungeon));
		baseGoal.add(andGoals);


		dungeon.addEntity(new Treasure(1, 2, dungeon));
		dungeon.addEntity(new Boulder(3, 2, dungeon));
		dungeon.addEntity(new Switch(4, 2, dungeon));
		dungeon.addEntity(new Exit(2, 1, dungeon));

		player.moveRight();
		assertFalse(dungeon.goalAchieved());

		player.moveUp();
		assertTrue(dungeon.goalAchieved());
	}

	/*
	 * Testing two level goals OR + (AND goals)
	 */
	@Test
	void testTwoLevelCompositeGoal2() {
		setup(5, 3, 2, 2);

		AndGoals andGoals = new AndGoals();
		andGoals.add(new SwitchGoal(dungeon));
		andGoals.add(new TreasureGoal(dungeon));
		OrGoals orGoals = new OrGoals();
		orGoals.add(new ExitGoal(dungeon));
		orGoals.add(andGoals);
		baseGoal.add(orGoals);


		dungeon.addEntity(new Treasure(1, 2, dungeon));
		dungeon.addEntity(new Boulder(3, 2, dungeon));
		dungeon.addEntity(new Switch(4, 2, dungeon));
		dungeon.addEntity(new Exit(2, 1, dungeon));

		player.moveUp();
		assertEquals(true, dungeon.goalAchieved()); // at exit true

		player.moveDown();
		assertEquals(false, dungeon.goalAchieved()); // move back to origin false

		player.moveRight();
		assertEquals(false, dungeon.goalAchieved()); // only pushes boulder false

		player.moveLeft(); // collected treasure
		assertEquals(true, dungeon.goalAchieved());

		player.moveLeft(); // dummy movement will result in true because the player has satisfied boulder +
							// treasure
		assertEquals(true, dungeon.goalAchieved());
	}
}
