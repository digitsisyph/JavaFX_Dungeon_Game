package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;

public class TreasureGoal extends GoalComponent {
	private Dungeon dungeon;

	public TreasureGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public boolean satisfied() {
		return dungeon.getTreasures().size() == 0;
	}

	@Override
	public void print() {
		System.out.println("TREASURE GOAL");
	}

}
