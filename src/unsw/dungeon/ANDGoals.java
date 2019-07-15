package unsw.dungeon;

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
		boolean result = true;
		for (GoalComponent goal : goals) {
			result = goal.satisfied();
			// every result must be true;
			if(result == false) {
				return false;
			}
		}
		return result;
	}

	@Override
	public void print() {
		System.out.println("ANDGoals: " + goals.size());
		for(GoalComponent goal : goals) {
			goal.print();
		}
	}
	
}
