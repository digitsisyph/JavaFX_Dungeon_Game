package unsw.dungeon;

public class TreasureGoal extends GoalComponent {
	private Dungeon dungeon;

	public TreasureGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public boolean satisfied() {
		if (dungeon.getTreasures().size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void print() {
		System.out.println("TREASURE GOAL");
	}

}
