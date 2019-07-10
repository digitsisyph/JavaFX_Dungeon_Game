package unsw.dungeon;

import javafx.geometry.Rectangle2D;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

	private Dungeon dungeon;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	public void moveUp() {

		if (getY() > 0) {
			int newY = getY() - 1;
			if (checkCollision(getX(), newY))
				y().set(newY);
		}
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e instanceof Player) {
				System.out.println(e.toString());
			}
		}
	}

	public void moveDown() {
		if (getY() < dungeon.getHeight() - 1) {
			int newY = getY() + 1;
			if (checkCollision(getX(), newY))
				y().set(newY);
		}
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e instanceof Player) {
				System.out.println(e.toString());
			}
		}
	}

	public void moveLeft() {
		if (getX() > 0) {
			int newX = getX() - 1;
			if (checkCollision(newX, getY()))
				x().set(newX);
		}
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e instanceof Player) {
				System.out.println(e.toString());
			}
		}
	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1) {
			int newX = getX() + 1;
			if (checkCollision(newX, getY()))
				x().set(newX);
		}
		for (Entity e : dungeon.getEntities()) {
			if (e != null && e instanceof Player) {
				System.out.println(e.toString());
			}
		}
	}

	@Override
	public String toString() {
		return "Player at X " + getX() + " Y " + getY();
	}

	private boolean checkCollision(int x, int y) {
		Rectangle2D rec = new Rectangle2D(x, y, 1, 1);
		for (Entity e : dungeon.getEntities()) {
			if (e instanceof Wall) {
				if (rec.intersects(e.getX(), e.getY(), 1, 1)) {
					System.out.println("Collision");
					return false;
				}
			}
		}
		return true;
	}
}
