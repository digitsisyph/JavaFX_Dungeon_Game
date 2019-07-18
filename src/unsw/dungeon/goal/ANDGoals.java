package unsw.dungeon.goal;

import java.util.ArrayList;

public class ANDGoals extends GoalComponent {
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
		return goals.stream().allMatch(goal -> goal.satisfied());
	}

	@Override
	public void print() {
		System.out.println("ANDGoals: " + goals.size());
		for(GoalComponent goal : goals) {
			goal.print();
		}
	}
	
}
