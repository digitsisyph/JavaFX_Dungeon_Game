package unsw.dungeon.entities.items;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Boulder;

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
