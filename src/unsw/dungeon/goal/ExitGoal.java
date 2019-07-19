package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.items.Exit;

import java.util.List;

public class ExitGoal implements Goal {

	private Dungeon dungeon;

	public ExitGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void add(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public void remove(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public boolean isSatisfied() {
		List<Entity> entitiesAtPlayers = dungeon.getEntities(dungeon.getPlayer().getX(), dungeon.getPlayer().getY());
		return entitiesAtPlayers.stream().anyMatch(entity -> entity instanceof Exit);
	}

	public void print() {
		System.out.println("EXIT GOAL");
	}

}
