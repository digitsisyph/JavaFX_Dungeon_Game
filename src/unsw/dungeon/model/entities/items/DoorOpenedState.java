package unsw.dungeon.model.entities.items;

public class DoorOpenedState implements DoorState {

	private Door door;

	DoorOpenedState(Door door) {
		this.door = door;
	}
	
	@Override
	public String getImagePath() {
		return "/open_door.png";
	}

	@Override
	public boolean canPassThrough() {
		return true;
	}

	@Override
	public void unlock() {
		System.out.println("Door is already opened");
	}

}
