package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Player;

public class Treasure extends Entity {

	public Treasure(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
	}

	@Override
	public String getImagePath() {
		return "/gold_pile.png";
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().pickUp(this);
	}

	@Override
	public EntityType type() {
		return EntityType.TREASURE;
	}

}
