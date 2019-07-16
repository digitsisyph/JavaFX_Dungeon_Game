package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Player;

public class Door extends Entity {

	private int id;

	public Door(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassable(false);
	}

	@Override
	public Image getImage() {
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
			this.getDungeon().removeEntity(this);
		}
	}

	public int getID() {
		return this.id;
	}
	@Override
	public EntityType type() {
		return EntityType.DOOR;
	}

}
