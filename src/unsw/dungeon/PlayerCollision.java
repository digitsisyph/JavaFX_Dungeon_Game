package unsw.dungeon;

import java.util.List;

import javafx.geometry.Rectangle2D;

public class PlayerCollision implements CollisionBehavior{

	@Override
	public boolean check(int x, int y, List<Entity> allEntities) {
		Rectangle2D rec = new Rectangle2D(x, y, 1, 1);
		for (Entity e : allEntities) {
			if (e instanceof Wall) {
				if (rec.intersects(e.getX(), e.getY(), 1, 1)) {
					System.out.println("Collision");
					return false;
				}
			}
		}
		return true;
	}

}
