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

	public void moveUp() {
		movement.moveUp();
		this.getDungeon().notifyMovement();
	}

	public void moveDown() {
		movement.moveDown();
		this.getDungeon().notifyMovement();
	}

	public void moveLeft() {
		movement.moveLeft();
		this.getDungeon().notifyMovement();
	}

	public void moveRight() {
		movement.moveRight();
		this.getDungeon().notifyMovement();
	}

	public void collideWith(Entity entity) {
		if (entity instanceof HumanEnemy)
			this.getDungeon().fightEnemy((HumanEnemy) entity);
	}

	@Override
	public EntityType type() {
		return EntityType.PLAYER;
	}
}
