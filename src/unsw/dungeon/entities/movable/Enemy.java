package unsw.dungeon.entities.movable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.movable.CollisionBehavior;

public class Enemy extends Entity implements Movable {

	private Dungeon dungeon;
	private MovementController movement;

	public Enemy(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.dungeon = dungeon;
		this.movement = new MovementController(dungeon);
		this.setPassable(false);
		// TODO Collision behavior for enemy
	}

	@Override
	public Image getImage() {
		return new Image("/deep_elf_master_archer.png");
	}


	// TODO this is just a very basic enemy AI, we can try to improve
	public void moveTowardsPlayer(Player player) {
		if (player.getX() > this.getX()
				&& dungeon.isWalkable(this.getX() + 1, this.getY())) {
			moveRight();
		} else if (player.getX() < this.getX()
				&& dungeon.isWalkable(this.getX() - 1, this.getY())) {
			moveLeft();
		} else if (player.getY() > this.getY()
				&& dungeon.isWalkable(this.getX(), this.getY() + 1))  {
			moveDown();
		} else if (player.getY() < this.getY()
				&& dungeon.isWalkable(this.getX(), this.getY() - 1)) {
			moveUp();
		}
	}

	public void moveAwayFromPlayer(Player player) {
		// TODO
	}

	public void moveUp() {
		System.out.println("Move UP");
		this.movement.moveUp(this);
	}

	public void moveDown() {
		System.out.println("Move DOWN");
		this.movement.moveDown(this);
	}

	public void moveLeft() {
		this.movement.moveLeft(this);
	}

	public void moveRight() {
		this.movement.moveRight(this);
	}

	// TODO
	public void collideWith(Entity entity) {
		//
	}
}
