package unsw.dungeon.entities.items;

public class DoorClosedState implements DoorState {

	Door door;

	public DoorClosedState(Door door) {
		this.door = door;
	}

	@Override
	public String getImage() {
		return "/closed_door.png";
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
