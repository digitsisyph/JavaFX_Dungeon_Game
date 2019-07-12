package unsw.dungeon;

import javafx.scene.image.Image;

public class Treasure extends Entity {

	public Treasure(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/gold_pile.png");
	}

}
