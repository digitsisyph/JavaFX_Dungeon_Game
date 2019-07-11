package unsw.dungeon;

import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public String toString() {
		return "Wall at X " + getX() + " Y " + getY();
	}

	@Override
	public Image getImage() {
		return new Image("/brick_brown_0.png");
	}
}
