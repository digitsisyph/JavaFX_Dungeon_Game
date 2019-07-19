package unsw.dungeon.model.goal;

public interface Goal {
	public void add(Goal goal);
	public void remove(Goal goal);
	public boolean isSatisfied();
	public void print();
}
