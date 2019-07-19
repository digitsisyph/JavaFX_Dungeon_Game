package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Potion extends Entity {

	public Potion(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.setImagePath("/brilliant_blue_new.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().pickUp(this);
	}

	@Override
	public EntityType type() {
		return EntityType.POTION;
	}

}
