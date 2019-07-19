package unsw.dungeon.model.entities.movable;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Movable {

	private Movement movement;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.movement = new Movement(this, dungeon);
		this.setPassThrough(false);
	}

	public void moveUp() {
		movement.moveUp();
		this.getDungeon().playerMovementUpdate();
	}

	public void moveDown() {
		movement.moveDown();
		this.getDungeon().playerMovementUpdate();
	}

	public void moveLeft() {
		movement.moveLeft();
		this.getDungeon().playerMovementUpdate();
	}

	public void moveRight() {
		movement.moveRight();
		this.getDungeon().playerMovementUpdate();
	}

	public void placeBomb() {
		this.getDungeon().playerPlacesBomb();
	}

	@Override
	public String toString() {
		return "Player at X " + getX() + " Y " + getY();
	}

	@Override
	public String getImagePath() {
		return "/human_new.png";
	}


	public void collideWith(Entity entity) {
		if (entity instanceof Enemy)
			this.getDungeon().fightEnemy((Enemy) entity);
	}

	@Override
	public EntityType type() {
		return EntityType.PLAYER;
	}
}
