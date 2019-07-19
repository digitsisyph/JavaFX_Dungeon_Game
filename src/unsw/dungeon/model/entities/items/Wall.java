package unsw.dungeon.model.entities.items;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;

public class Wall extends Entity {

    public Wall(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
		this.setPassThrough(false);
    }

	@Override
	public String toString() {
		return "Wall at X " + getX() + " Y " + getY();
	}

	@Override
	public String getImagePath() {
		return "/brick_brown_0.png";
	}

	public void collideWith(Entity entity) {;}

	@Override
	public EntityType type() {
		return EntityType.WALL;
	}
}
