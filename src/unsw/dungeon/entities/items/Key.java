package unsw.dungeon.entities.items;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Player;

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
