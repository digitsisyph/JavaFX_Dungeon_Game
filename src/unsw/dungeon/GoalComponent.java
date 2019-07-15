package unsw.dungeon;

public abstract class GoalComponent {

	public void add(GoalComponent goalComponent) {
		throw new UnsupportedOperationException();
	}

	public void remove(GoalComponent goalComponent) {
		throw new UnsupportedOperationException();
	}

	public GoalComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}

	public boolean satisfied() {
		throw new UnsupportedOperationException();
	}

	public void print() {
		throw new UnsupportedOperationException();
	}
}
