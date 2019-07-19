package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;

public class EnemyGoal implements Goal {

	private Dungeon dungeon;

	public EnemyGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void add(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public void remove(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public boolean isSatisfied() {
		return dungeon.getEnemies().size() == 0;
	}

	public void print() {
		System.out.println("ENEMY GOAL");
	}
}
