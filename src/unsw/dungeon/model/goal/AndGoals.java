package unsw.dungeon.model.goal;

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

	public void update() {
		if (this.isSatisfied()) {
			System.out.println("Goal Achieved!");
		}
	}

	@Override
	public void print() {
		System.out.println("AndGoals: " + goals.size());
		for(Goal goal : goals)
			goal.print();
	}
	
}
