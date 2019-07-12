package unsw.dungeon;

import javafx.scene.image.Image;

public class Enemy extends Entity {

	private Dungeon dungeon;
	private CollisionBehavior collision;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.collision = null;
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
}
