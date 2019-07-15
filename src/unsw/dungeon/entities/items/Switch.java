package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Boulder;
import unsw.dungeon.entities.movable.Player;

public class Switch extends Entity {

	Dungeon dungeon;

	public Switch(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.setPassable(true);
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/pressure_plate.png");
	}

	// TODO
	public void collideWith(Entity entity) {
		//
	}

	@Override
	public EntityType type() {
		return EntityType.SWITCH;
	}
}
