package unsw.dungeon.entities.movable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;

public class Boulder extends Entity implements Movable {

	MovementController movement;

	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.setPassable(false);
		this.movement = new MovementController(dungeon);
	}

	@Override
	public Image getImage() {
		return new Image("/boulder.png");
	}

	public void moveUp() {
		System.out.println("Boulder be pushed UP");
		this.movement.moveUp(this);
	}

	public void moveDown() {
		System.out.println("Boulder be pushed DOWN");
		this.movement.moveDown(this);
	}

	public void moveLeft() {
		System.out.println("Boulder be pushed LEFT");
		this.movement.moveLeft(this);
	}

	public void moveRight() {
		System.out.println("Boulder be pushed RIGHT");
		this.movement.moveRight(this);
	}

	// TODO
	public void collideWith(Entity entity) {
		// pass
	}

	public void bePushed(Entity entity) {
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

}
