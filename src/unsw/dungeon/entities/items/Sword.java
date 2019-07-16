package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Player;

public class Sword extends Entity {

	public Sword(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassable(true);
	}

	@Override
	public Image getImage() {
		return new Image("/greatsword_1_new.png");
	}

	// TODO
	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			this.getDungeon().pickSword(this);
		}
	}

	@Override
	public EntityType type() {
		return EntityType.SWORD;
	}

}
