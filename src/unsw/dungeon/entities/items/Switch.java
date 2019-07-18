package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;

public class Switch extends Entity {

	public Switch(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
	}

	@Override
	public Image getImage() {
		return new Image("/pressure_plate.png");
	}

	public void collideWith(Entity entity) {
		// TODO
	}

	@Override
	public EntityType type() {
		return EntityType.SWITCH;
	}
}
