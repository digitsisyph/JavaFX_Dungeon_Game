package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.enemies.HumanEnemy;

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
		this.setImagePath("/human_new.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof HumanEnemy)
			this.getDungeon().fightEnemy((HumanEnemy) entity);
	}

	@Override
	public EntityType type() {
		return EntityType.PLAYER;
	}

	// for movable interface

	public void moveUp() {
		movement.moveUp();
	}

	public void moveDown() {
		movement.moveDown();
	}

	public void moveLeft() {
		movement.moveLeft();
	}

	public void moveRight() {
		movement.moveRight();
	}
}
