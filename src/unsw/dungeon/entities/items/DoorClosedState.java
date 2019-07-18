package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class DoorClosedState implements DoorState {

	private Door door;

	DoorClosedState(Door door) {
		this.door = door;
	}

	@Override
	public Image getImage() {
		return new Image("/closed_door.png");
	}

	@Override
	public boolean canPassThrough() {
		return false;
	}

	@Override
	public void unlock() {
		door.setState(new DoorOpenedState(door));
		door.getDungeon().updateGridImage(door, getImage());
	}

}
