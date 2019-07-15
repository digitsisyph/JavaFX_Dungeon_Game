package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.movable.Player;

public class Exit extends Entity {

	Dungeon dungeon;

	public Exit(int x, int y, Dungeon dungeon) {
		super(x, y);
		this.setPassable(true);
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/exit.png");
	}

	// TODO
	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			// TODO check other goals
		}
	}

}
