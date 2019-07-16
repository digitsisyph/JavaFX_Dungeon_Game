package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public interface DoorState {
	public Image getImage();
	public boolean passThrough();
	public void unlock();
}
