package unsw.dungeon.model.goal;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.Exit;

import java.util.List;

public class ExitGoal extends Goal {

	public ExitGoal(Dungeon dungeon) {
		super(dungeon);
		setIsLeaf(true);
	}

	public void update() {
		List<Entity> entitiesAtPlayers = getDungeon().getEntities(getDungeon().getPlayer().getX(), getDungeon().getPlayer().getY());
		setSatisfied(entitiesAtPlayers.stream()
				.anyMatch(entity -> entity instanceof Exit));
	}

	@Override
	public String toString() {
		return "Go to the Exit";
	}

}
