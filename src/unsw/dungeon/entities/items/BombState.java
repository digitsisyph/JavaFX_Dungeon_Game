package unsw.dungeon.entities.items;

public interface BombState {
	public String getImage();
	public boolean passThrough();
	public boolean next();
	public void lit();
}
