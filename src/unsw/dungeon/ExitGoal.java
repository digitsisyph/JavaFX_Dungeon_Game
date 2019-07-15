package unsw.dungeon;

import java.util.List;

import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;

public class ExitGoal extends GoalComponent {

	private Dungeon dungeon;

	public ExitGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public boolean satisfied() {
		List<Entity> entitiesAtPlayers = dungeon.getEntities(dungeon.getPlayer().getX(), dungeon.getPlayer().getY());
		for (Entity e : entitiesAtPlayers) {
			if(e.type() == EntityType.EXIT) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void print() {
	}

}
