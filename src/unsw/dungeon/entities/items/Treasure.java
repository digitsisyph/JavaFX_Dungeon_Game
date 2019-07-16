package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Player;

public class Treasure extends Entity {

	public Treasure(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassable(true);
	}

	@Override
	public Image getImage() {
		return new Image("/gold_pile.png");
	}

	// TODO
	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			this.getDungeon().pickTreasure(this);
		}
	}

	@Override
	public EntityType type() {
		return EntityType.TREASURE;
	}

}
