package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Player;

public class Bomb extends Entity {

	public Bomb(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassable(true);
	}

	@Override
	public Image getImage() {
		// Image of unlit bomb
		return new Image("/bomb_unlit.png");
	}
	// TODO Apply State Pattern

	// TODO
	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			this.getDungeon().pickBomb(this);
		}
	}

	@Override
	public EntityType type() {
		return EntityType.BOMB;
	}
}
