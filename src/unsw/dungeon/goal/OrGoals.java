package unsw.dungeon.goal;

import java.util.ArrayList;
import java.util.List;

public class OrGoals implements Goal {

	List<Goal> goals = new ArrayList<Goal>();

	public void add(Goal goal) {
		goals.add(goal);
	}

	public void remove(Goal goal) {
		goals.remove(goal);
	}

	public boolean isSatisfied() {
		return goals.stream().anyMatch(goal -> goal.isSatisfied());
	}

	public void print() {
		System.out.print("OrGoals: " + goals.size());
		for(Goal goal : goals) {
			goal.print();
		}
	}

}
