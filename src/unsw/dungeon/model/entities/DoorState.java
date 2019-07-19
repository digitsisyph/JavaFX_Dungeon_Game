package unsw.dungeon.model.entities;

public interface DoorState {
	public String getImagePath();
	public boolean canPassThrough();
	public void unlock();
}
