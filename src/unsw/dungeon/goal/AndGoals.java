package unsw.dungeon.goal;

import java.util.ArrayList;
import java.util.List;

public class AndGoals implements Goal {

	List<Goal> goals = new ArrayList<Goal>();

	@Override
	public void add(Goal goal) {
		goals.add(goal);
	}

	@Override
	public void remove(Goal goal) {
		goals.remove(goal);
	}

	@Override
	public boolean isSatisfied() {
		return goals.stream().allMatch(goal -> goal.isSatisfied());
	}

	@Override
	public void print() {
		System.out.println("AndGoals: " + goals.size());
		for(Goal goal : goals)
			goal.print();
	}
	
}
