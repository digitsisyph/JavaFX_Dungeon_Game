package unsw.dungeon.entities.movable;

import unsw.dungeon.entities.Entity;

import java.util.List;

public interface CollisionBehavior {
	public boolean check(int x, int y, List<Entity> allEntities);
}
