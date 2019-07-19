package unsw.dungeon.model.goal;

import unsw.dungeon.model.Dungeon;

public class BoulderGoal implements Goal {

	private Dungeon dungeon;

	public BoulderGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void add(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public void remove(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public boolean isSatisfied() {
		return dungeon.getSwitches().stream().allMatch(s -> s.isActivated());
	}

	public void print() {
		System.out.println("BOULDER GOAL");
	}
}
