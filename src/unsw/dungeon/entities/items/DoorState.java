package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public interface DoorState {
	public Image getImage();
	public boolean canPassThrough();
	public void unlock();
}
