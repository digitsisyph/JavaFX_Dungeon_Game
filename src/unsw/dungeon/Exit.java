package unsw.dungeon;

import javafx.scene.image.Image;

public class Exit extends Entity {

	public Exit(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/exit.png");
	}

}
