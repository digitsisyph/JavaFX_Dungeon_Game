package unsw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.model.entities.Boulder;
import unsw.dungeon.model.entities.Enemy;
import unsw.dungeon.model.entities.Exit;
import unsw.dungeon.model.entities.Switch;
import unsw.dungeon.model.entities.Sword;
import unsw.dungeon.model.entities.Treasure;
import unsw.dungeon.model.goal.AndGoals;
import unsw.dungeon.model.goal.BoulderGoal;
import unsw.dungeon.model.goal.EnemyGoal;
import unsw.dungeon.model.goal.ExitGoal;
import unsw.dungeon.model.goal.OrGoals;
import unsw.dungeon.model.goal.TreasureGoal;

public class testGoals extends testSetup {

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

	@Test
	void testEnemyGoal() {
		setup(5, 1, 0, 0);
		baseGoal.add(new EnemyGoal(dungeon));
		Enemy enemy = new Enemy(3, 0, dungeon);
		dungeon.addEntity(enemy);
		enemy = new Enemy(4, 0, dungeon);
		dungeon.addEntity(enemy);
		Sword sword = new Sword(1, 0, dungeon);
		dungeon.addEntity(sword);
		player.moveRight(); // pick sword
		assertEquals(false, dungeon.goalAchieved());
		player.moveRight();
		player.moveRight();
		assertEquals(true, dungeon.goalAchieved());

	}

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

	@Test
	void testExitGoal() {
		setup(5, 5, 2, 2);
		baseGoal.add(new ExitGoal(dungeon));
		Exit exit = new Exit(2,1,dungeon);
		dungeon.addEntity(exit);
		assertEquals(false, dungeon.goalAchieved());
		player.moveUp();
		assertEquals(true, dungeon.goalAchieved());
	}

	@Test
	void testOneLevel() {
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
	
	
	@Test
	void testTwoLevel() {
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
	@Test
	void testTwoLevel2() {
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
		player.moveLeft();
		assertEquals(false, dungeon.goalAchieved());
		player.moveRight();
		player.moveUp();
		assertEquals(true, dungeon.goalAchieved());
	}
}
