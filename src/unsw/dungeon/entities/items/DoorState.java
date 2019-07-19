package unsw.dungeon.entities.items;

public interface DoorState {
	public String getImagePath();
	public boolean canPassThrough();
	public void unlock();
}
