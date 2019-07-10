package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public String toString() {
		return "Wall at X " + getX() + " Y " + getY();
	}
}
