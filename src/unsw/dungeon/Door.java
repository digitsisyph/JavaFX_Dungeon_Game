package unsw.dungeon;

import javafx.scene.image.Image;

public class Door extends Entity {

	public Door(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/closed_door.png");
	}

}
