package unsw.dungeon.entities.movable;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;

public class Boulder extends Entity {

	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.setPassable(false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/boulder.png");
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
		// pass
	}

}
