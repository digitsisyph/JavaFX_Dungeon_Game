package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class ClosedDoorState implements DoorState {

	Door door;

	public ClosedDoorState(Door door) {
		this.door = door;
	}

	@Override
	public Image getImage() {
		return new Image("/closed_door.png");
	}

	@Override
	public boolean passThrough() {
		return false;
	}

	@Override
	public void unlock() {
		door.setState(door.getOpenedDoor());
	}

}
