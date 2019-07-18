package unsw.dungeon.entities.items;

public class DoorOpenedState implements DoorState {

	Door door;

	public DoorOpenedState(Door door) {
		this.door = door;
	}
	
	@Override
	public String getImage() {
		return "/open_door.png";
	}

	@Override
	public boolean passThrough() {
		return true;
	}

	@Override
	public void unlock() {
		System.out.println("Door is opened");
	}

}
