package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;

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
	public Image getImage() {
		return new Image("/brick_brown_0.png");
	}

	public void collideWith(Entity entity) {;}

	@Override
	public EntityType type() {
		return EntityType.WALL;
	}
}
