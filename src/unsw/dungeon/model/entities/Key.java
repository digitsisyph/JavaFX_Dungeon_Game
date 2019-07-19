package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Player;

public class Key extends Entity {

	private int id;

	public Key(int x, int y, Dungeon dungeon, int id) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.id = id;
	}

	@Override
	public String getImagePath() {
		return "/key.png";
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().pickUp(this);
	}

	@Override
	public EntityType type() {
		return EntityType.KEY;
	}

	public int getId() {
		return id;
	}
}
