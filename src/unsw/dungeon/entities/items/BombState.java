package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public interface BombState {
	public Image getImage();
	public boolean passThrough();
	public void next();
	public void lit();
}
