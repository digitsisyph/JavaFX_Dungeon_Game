package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.enemies.Enemy;
import unsw.dungeon.model.entities.enemies.HumanEnemy;
import unsw.dungeon.model.goal.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testGoals extends testSetup {

	/*
	 * Collect all treasures to complete the goal
	 */
	@Test
	void testTreasureGoal() {
		setup(5, 3, 1, 1);
		baseGoal.add(new TreasureGoal(dungeon));
		Treasure treasure = new Treasure(2, 1, dungeon);
		dungeon.addEntity(treasure);
		treasure = new Treasure(3, 1, dungeon);
		dungeon.addEntity(treasure);
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
		Enemy enemy = new HumanEnemy(1, 0, dungeon);
		dungeon.addEntity(enemy);
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
		setup(5, 5, 2, 2);
		baseGoal.add(new BoulderGoal(dungeon));
		Switch sw = new Switch(2, 0, dungeon);
		dungeon.addEntity(sw);
		sw = new Switch(4, 2, dungeon);
		dungeon.addEntity(sw);
		Boulder boulder = new Boulder(3, 2, dungeon);
		dungeon.addEntity(boulder);
		boulder = new Boulder(2, 1, dungeon);
		dungeon.addEntity(boulder);
		assertEquals(false, dungeon.goalAchieved());
		player.moveRight();
		player.moveUp();
		assertEquals(true, dungeon.goalAchieved());
	}

	/*
	 * Be at the exit to complete the goal
	 */
	@Test
	void testExitGoal() {
		setup(5, 5, 2, 2);
		baseGoal.add(new ExitGoal(dungeon));
		Exit exit = new Exit(2, 1, dungeon);
		dungeon.addEntity(exit);
		assertEquals(false, dungeon.goalAchieved());
		player.moveUp();
		assertEquals(true, dungeon.goalAchieved());
	}

	/*
	 * Testing 2 AND goals
	 */
	@Test
	void testOneLevelCompositeGoal() {
		setup(5, 3, 1, 1);
		AndGoals andGoals = new AndGoals();
		andGoals.add(new ExitGoal(dungeon));
		andGoals.add(new TreasureGoal(dungeon));
		baseGoal.add(andGoals);
		Treasure treasure = new Treasure(2, 1, dungeon);
		dungeon.addEntity(treasure);
		Exit exit = new Exit(3, 1, dungeon);
		dungeon.addEntity(exit);
		player.moveRight();
		assertEquals(false, dungeon.goalAchieved());
		player.moveRight();
		assertEquals(true, dungeon.goalAchieved());
	}

	/*
	 * Testing two level goals AND + (OR goals)
	 */
	@Test
	void testTwoLevelCompositeGoal1() {
		setup(5, 3, 2, 2);
		AndGoals andGoals = new AndGoals();
		OrGoals orGoals = new OrGoals();
		baseGoal.add(andGoals);
		andGoals.add(new ExitGoal(dungeon));
		andGoals.add(orGoals);
		orGoals.add(new BoulderGoal(dungeon));
		orGoals.add(new TreasureGoal(dungeon));
		Treasure treasure = new Treasure(1, 2, dungeon);
		Boulder boulder = new Boulder(3, 2, dungeon);
		Switch sw = new Switch(4, 2, dungeon);
		Exit exit = new Exit(2, 1, dungeon);
		dungeon.addEntity(treasure);
		dungeon.addEntity(exit);
		dungeon.addEntity(boulder);
		dungeon.addEntity(sw);
		player.moveRight();
		assertEquals(false, dungeon.goalAchieved());
		player.moveUp();
		assertEquals(true, dungeon.goalAchieved());
	}

	/*
	 * Testing two level goals OR + (AND goals)
	 */
	@Test
	void testTwoLevelCompositeGoal2() {
		setup(5, 3, 2, 2);
		AndGoals andGoals = new AndGoals();
		OrGoals orGoals = new OrGoals();
		baseGoal.add(orGoals);
		orGoals.add(new ExitGoal(dungeon));
		orGoals.add(andGoals);
		andGoals.add(new BoulderGoal(dungeon));
		andGoals.add(new TreasureGoal(dungeon));
		Treasure treasure = new Treasure(1, 2, dungeon);
		Boulder boulder = new Boulder(3, 2, dungeon);
		Switch sw = new Switch(4, 2, dungeon);
		Exit exit = new Exit(2, 1, dungeon);
		dungeon.addEntity(treasure);
		dungeon.addEntity(exit);
		dungeon.addEntity(boulder);
		dungeon.addEntity(sw);
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
