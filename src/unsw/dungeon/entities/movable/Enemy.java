package unsw.dungeon.entities.movable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.movable.CollisionBehavior;

public class Enemy extends Entity {

	private Dungeon dungeon;
	private CollisionBehavior collision;

	public Enemy(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.dungeon = dungeon;
		this.collision = null;
		this.setPassable(false);
		// TODO Collision behavior for enemy
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/deep_elf_master_archer.png");
	}

	public void moveUp() {
		// TODO
	}

	public void moveDown() {
		// TODO
	}

	public void moveLeft() {
		// TODO
	}

	public void moveRight() {
		// TODO
	}

	// TODO
	public void collideWith(Entity entity) {
		//
	}
}
