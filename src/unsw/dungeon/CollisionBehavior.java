package unsw.dungeon;

import java.util.List;

public interface CollisionBehavior {
	public boolean check(int x, int y, List<Entity> allEntities);
}
