package unsw.dungeon.entities.movable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Movable {

	private MovementController movement;
	private Dungeon dungeon;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.dungeon = dungeon;
		this.movement = new MovementController(dungeon);
		this.setPassable(false);
	}

	public void moveUp() {
		movement.moveUp(this);
		this.dungeon.playerMovementUpdate();
	}

	public void moveDown() {
		movement.moveDown(this);
		this.dungeon.playerMovementUpdate();
	}

	public void moveLeft() {
		movement.moveLeft(this);
		this.dungeon.playerMovementUpdate();
	}

	public void moveRight() {
		movement.moveRight(this);
		this.dungeon.playerMovementUpdate();
	}

	@Override
	public String toString() {
		return "Player at X " + getX() + " Y " + getY();
	}

	@Override
	public Image getImage() {
		return new Image("/human_new.png");
	}

	// TODO
	public void collideWith(Entity entity) {
		//
	}
}
