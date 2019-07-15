package unsw.dungeon;

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
		boolean result = false;
		for (GoalComponent goal : goals) {
			result = goal.satisfied();
			// only one result must be true;
			if (result == true) {
				return true;
			}
		}
		return result;
	}

	@Override
	public void print() {
		System.out.println("ANDGoals: " + goals.size());
	}

}
