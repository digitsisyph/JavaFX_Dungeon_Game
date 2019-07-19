package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;

public class Exit extends Entity {

	public Exit(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
	}

	@Override
	public Image getImage() {
		return new Image("/exit.png");
	}

	public void collideWith(Entity entity) {;}

	@Override
	public EntityType type() {
		return EntityType.EXIT;
	}

}
