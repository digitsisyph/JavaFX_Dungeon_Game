package unsw.dungeon.goal;

import java.util.ArrayList;

public class ORGoals extends GoalComponent {

	ArrayList<GoalComponent> goals = new ArrayList<>();

	@Override
	public void add(GoalComponent goalComponent) {
		goals.add(goalComponent);
	}

	@Override
	public void remove(GoalComponent goalComponent) {
		goals.remove(goalComponent);
	}

	@Override
	public GoalComponent getChild(int i) {
		return goals.get(i);
	}

	@Override
	public boolean satisfied() {
		return goals.stream().anyMatch(goal -> goal.satisfied());
	}

	@Override
	public void print() {
		System.out.print("ORGoals: " + goals.size());
		for(GoalComponent goal : goals) {
			goal.print();
		}
	}

}
