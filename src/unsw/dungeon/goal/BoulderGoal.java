package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;

public class BoulderGoal extends GoalComponent {

	private Dungeon dungeon;

	public BoulderGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public boolean satisfied() {
		return dungeon.getSwitches().stream().allMatch(s -> s.isActivated());
	}

	@Override
	public void print() {
		System.out.println("BOULDER GOAL");
	}
}
