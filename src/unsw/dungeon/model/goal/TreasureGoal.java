package unsw.dungeon.model.goal;

import unsw.dungeon.model.Dungeon;

public class TreasureGoal implements Goal {
	private Dungeon dungeon;

	public TreasureGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void add(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public void remove(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public boolean isSatisfied() {
		return dungeon.getTreasures().size() == 0;
	}

	public void print() {
		System.out.println("TREASURE GOAL");
	}

}
