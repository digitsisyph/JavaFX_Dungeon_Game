package unsw.dungeon.model.entities.items;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.movable.Boulder;

public class Switch extends Entity {

	public Switch(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
	}

	@Override
	public String getImagePath() {
		return "/pressure_plate.png";
	}

	public void collideWith(Entity entity) {;}

	public boolean isActivated() {
		return  getDungeon().getEntities(getX(), getY()).stream()
				.anyMatch(entity -> entity instanceof Boulder);
	}

	@Override
	public EntityType type() {
		return EntityType.SWITCH;
	}
}
