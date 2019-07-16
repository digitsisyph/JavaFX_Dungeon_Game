package unsw.dungeon.entities.movable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.CollisionBehavior;
import java.util.Random;

public class Enemy extends Entity implements Movable {

	private MovementController movement;
	// for random movement
	private Random rand = new Random();

	public Enemy(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.movement = new MovementController(dungeon);
		this.setPassable(false);
		// TODO Collision behavior for enemy
	}

	@Override
	public Image getImage() {
		return new Image("/deep_elf_master_archer.png");
	}


	// TODO this is just a very basic enemy AI, we can try to improve
	// added a random movement
	public void moveTowardsPlayer(Player player) {
		int ran = rand.nextInt(2);

		if (ran == 0) {
			if (player.getX() > this.getX()
					&& getDungeon().isWalkable(this.getX() + 1, this.getY())) {
				moveRight();
			} else if (player.getX() < this.getX()
					&& getDungeon().isWalkable(this.getX() - 1, this.getY())) {
				moveLeft();
			} else if (player.getY() > this.getY()
					&& getDungeon().isWalkable(this.getX(), this.getY() + 1)) {
				moveDown();
			} else if (player.getY() < this.getY()
					&& getDungeon().isWalkable(this.getX(), this.getY() - 1)) {
				moveUp();
			}
		} else {
			if (player.getY() > this.getY()
					&& getDungeon().isWalkable(this.getX(), this.getY() + 1)) {
				moveDown();
			} else if (player.getY() < this.getY()
					&& getDungeon().isWalkable(this.getX(), this.getY() - 1)) {
				moveUp();
			} else if (player.getX() > this.getX()
					&& getDungeon().isWalkable(this.getX() + 1, this.getY())) {
				moveRight();
			} else if (player.getX() < this.getX()
					&& getDungeon().isWalkable(this.getX() - 1, this.getY())) {
				moveLeft();
			}
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
		if (entity instanceof Player) {
			this.getDungeon().fightEnemy(this);
		}
	}

	@Override
	public EntityType type() {
		return EntityType.ENEMY;
	}
}
