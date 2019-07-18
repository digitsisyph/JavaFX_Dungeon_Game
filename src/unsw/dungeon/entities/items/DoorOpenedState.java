package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class DoorOpenedState implements DoorState {

	private Door door;

	DoorOpenedState(Door door) {
		this.door = door;
	}
	
	@Override
	public Image getImage() {
		return new Image("/open_door.png");
	}

	@Override
	public boolean canPassThrough() {
		return true;
	}

	@Override
	public void unlock() {
		System.out.println("Door is opened");
	}

}
