package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;

public class EnemyGoal extends GoalComponent {

	private Dungeon dungeon;

	public EnemyGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public boolean satisfied() {
		return dungeon.getEnemies().size() == 0;
	}

	@Override
	public void print() {
		System.out.println("ENEMY GOAL");
	}
}
