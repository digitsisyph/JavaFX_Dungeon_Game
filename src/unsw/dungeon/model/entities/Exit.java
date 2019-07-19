package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;

public class Exit extends Entity {

	public Exit(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
	}

	@Override
	public String getImagePath() {
		return "/exit.png";
	}

	public void collideWith(Entity entity) {;}

	@Override
	public EntityType type() {
		return EntityType.EXIT;
	}

}
