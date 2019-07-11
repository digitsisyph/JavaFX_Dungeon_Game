package unsw.dungeon;

import javafx.scene.image.Image;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

	private Dungeon dungeon;
	private CollisionBehavior collision;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.collision = new PlayerCollision();
	}

	public void moveUp() {

		if (getY() > 0) {
			int newY = getY() - 1;
			if (collision.check(getX(), newY, dungeon.getEntities()))
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
			if (collision.check(getX(), newY, dungeon.getEntities()))
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
			if (collision.check(newX, getY(), dungeon.getEntities()))
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
			if (collision.check(newX, getY(), dungeon.getEntities()))
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

	@Override
	public Image getImage() {
		return new Image("/human_new.png");
	}
}
