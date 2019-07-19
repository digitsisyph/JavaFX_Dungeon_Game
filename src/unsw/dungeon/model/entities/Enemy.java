package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Enemy extends Entity implements Movable {

	private Movement movement;
	private EnemyBehaviour behaviour;

	public Enemy(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.movement = new Movement(this, dungeon);
		this.setPassThrough(false);
		this.behaviour = new EnemyMoveClose();
	}

	@Override
	public String getImagePath() {
		return "/deep_elf_master_archer.png";
	}

	public void move(Player player) {
		behaviour.move(this, player);
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
			this.getDungeon().fightEnemy(this);
		}
	}
	/**
	 * @param behaviour the behaviour to set
	 */
	public void setBehaviour(EnemyBehaviour behaviour) {
		this.behaviour = behaviour;
	}

	@Override
	public EntityType type() {
		return EntityType.ENEMY;
	}
}
