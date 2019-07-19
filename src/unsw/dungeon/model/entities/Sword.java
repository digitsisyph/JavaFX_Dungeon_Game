package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Player;

public class Sword extends Entity {

	public Sword(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
	}

	@Override
	public String getImagePath() {
		return "/greatsword_1_new.png";
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().pickUp(this);
	}

	@Override
	public EntityType type() {
		return EntityType.SWORD;
	}

}
