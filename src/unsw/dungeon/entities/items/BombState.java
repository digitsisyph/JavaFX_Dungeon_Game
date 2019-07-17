package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public interface BombState {
	public Image getImage();
	public boolean passThrough();
	public boolean next();
	public void lit();
}
