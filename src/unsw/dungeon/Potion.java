package unsw.dungeon;

import javafx.scene.image.Image;

public class Potion extends Entity {

	public Potion(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/brilliant_blue_new.png");
	}

}
