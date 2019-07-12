package unsw.dungeon;

import javafx.scene.image.Image;

public class Bomb extends Entity {

	public Bomb(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// Image of unlit bomb
		return new Image("/bomb_unlit.png");
	}
	// TODO Apply State Pattern
}
