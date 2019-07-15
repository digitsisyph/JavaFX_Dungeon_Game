package unsw.dungeon;

public class EnemyGoal extends GoalComponent {
	private Dungeon dungeon;

	public EnemyGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public boolean satisfied() {
		if (dungeon.getEnemies().size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void print() {
		System.out.println("ENEMY GOAL");
	}
}
