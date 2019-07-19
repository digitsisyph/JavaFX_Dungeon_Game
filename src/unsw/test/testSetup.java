package unsw.test;

import org.junit.jupiter.api.AfterEach;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Player;
import unsw.dungeon.model.goal.AndGoals;
import unsw.dungeon.model.goal.Goal;

public class testSetup {

	Dungeon dungeon = null;
	Player player = null;

	void setup(int width, int height, int playerX, int playerY) {
		dungeon = new Dungeon(width, height);
		player = new Player(playerX, playerY, dungeon);
		dungeon.setPlayer(player);
		Goal dummy = new AndGoals();
		dungeon.setGoal(dummy);
	}

	@AfterEach
	void teatDown() {
		dungeon = null;
		player = null;
	}
}
