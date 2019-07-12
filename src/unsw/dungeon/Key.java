package unsw.dungeon;

import javafx.scene.image.Image;

public class Key extends Entity {

	public Key(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/key.png");
	}

}
