package unsw.dungeon.entities.movable;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;

public class Boulder extends Entity implements Movable {

	Movement movement;

	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(false);
		this.movement = new Movement(dungeon);
	}

	@Override
	public String getImagePath() {
		return "/boulder.png";
	}

	public void moveUp() {
		this.movement.moveUp(this);
	}

	public void moveDown() {
		this.movement.moveDown(this);
	}

	public void moveLeft() {
		this.movement.moveLeft(this);
	}

	public void moveRight() {
		this.movement.moveRight(this);
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
