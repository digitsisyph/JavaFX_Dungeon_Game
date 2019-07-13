package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.movable.Player;

public class Door extends Entity {

	Dungeon dungeon;

	private int id;

	public Door(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.setPassable(false);
		this.dungeon = dungeon;
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/closed_door.png");
	}

	public void open() {
		this.setPassable(true);
		// TODO change image
		((ImageView) this.getNode()).setImage(new Image("/opened_door.png"));
	}

	// TODO
	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			this.dungeon.removeEntity(this);
		}
	}

	public int getID() {
		return this.id;
	}

}
