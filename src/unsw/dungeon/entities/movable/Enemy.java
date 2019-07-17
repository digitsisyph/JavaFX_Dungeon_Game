package unsw.dungeon.entities.movable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;

public class Enemy extends Entity implements Movable {

	private MovementController movement;
	private MovementBehaviour behaviour;
	public Enemy(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.movement = new MovementController(dungeon);
		this.setPassThrough(false);
		this.behaviour = new MoveTowardsPlayer();
	}

	@Override
	public Image getImage() {
		return new Image("/deep_elf_master_archer.png");
	}

	public void move(Player player) {
		behaviour.move(this, player);
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

	// TODO
	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			this.getDungeon().fightEnemy(this);
		}
	}
	/**
	 * @param behaviour the behaviour to set
	 */
	public void setBehaviour(MovementBehaviour behaviour) {
		this.behaviour = behaviour;
	}

	@Override
	public EntityType type() {
		return EntityType.ENEMY;
	}
}
