package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Player;

public class Key extends Entity {

	private int id;

	public Key(int x, int y, Dungeon dungeon, int id) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.id = id;
	}

	@Override
	public Image getImage() {
		return new Image("/key.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			this.getDungeon().pickUpKey(this);
		}
	}

	@Override
	public EntityType type() {
		return EntityType.KEY;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
