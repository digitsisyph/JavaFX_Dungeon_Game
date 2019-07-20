package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Boulder extends Entity implements Movable {

	private Movement movement;

	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		movement = new Movement(this, dungeon);
		setPassThrough(false);
		setImagePath("/boulder.png");
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

	// for movable interface

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

}
