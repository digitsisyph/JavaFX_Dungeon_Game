package unsw.dungeon.entities.items;

public interface DoorState {
	public String getImage();
	public boolean passThrough();
	public void unlock();
}
