package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;

public class UnlitBomb extends Entity {

	public UnlitBomb(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
	}

	@Override
	public Image getImage() {
		return new Image("/bomb_unlit.png");
	}

	public void collideWith(Entity entity) {
		this.getDungeon().pickUpBomb(this);
	}

	@Override
	public EntityType type() {
		return EntityType.BOMB;
	}

}
