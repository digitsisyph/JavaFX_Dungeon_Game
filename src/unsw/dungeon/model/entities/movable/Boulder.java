package unsw.dungeon.model.entities.movable;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;

public class Boulder extends Entity implements Movable {

	private Movement movement;

	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(false);
		this.movement = new Movement(this, dungeon);
	}

	@Override
	public String getImagePath() {
		return "/boulder.png";
	}

	public void moveUp() {
		this.movement.moveUp();
	}

	public void moveDown() {
		this.movement.moveDown();
	}

	public void moveLeft() {
		this.movement.moveLeft();
	}

	public void moveRight() {
		this.movement.moveRight();
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			if (entity.getY() > this.getY()) {
				this.moveUp();
			} else if (entity.getY() < this.getY()) {
				this.moveDown();
			} else if (entity.getX() < this.getX()) {
				this.moveRight();
			} else if (entity.getX() > this.getX()) {
				this.moveLeft();
			}
		}
	}

	@Override
	public EntityType type() {
		return EntityType.BOULDER;
	}

}
