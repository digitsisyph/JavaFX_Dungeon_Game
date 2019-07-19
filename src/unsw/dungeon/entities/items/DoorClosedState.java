package unsw.dungeon.entities.items;

public class DoorClosedState implements DoorState {

	private Door door;

	DoorClosedState(Door door) {
		this.door = door;
	}

	@Override
	public String getImagePath() {
		return "/closed_door.png";
	}

	@Override
	public boolean canPassThrough() {
		return false;
	}

	@Override
	public void unlock() {
		door.setState(new DoorOpenedState(door));
		door.getDungeon().updateGridImage(door, getImagePath());
	}

}
