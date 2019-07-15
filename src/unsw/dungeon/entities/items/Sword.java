package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.movable.Player;

public class Sword extends Entity {

	private int durability;
	Dungeon dungeon;

	public Sword(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.durability = 5; // default to 5
		this.setPassable(true);
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/greatsword_1_new.png");
	}

	// TODO
	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			this.dungeon.removeEntity(this);
		}
	}

}
