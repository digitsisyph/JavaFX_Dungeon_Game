package unsw.dungeon;

import java.util.List;

import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.items.Switch;

public class BoulderGoal extends GoalComponent {
	private Dungeon dungeon;

	public BoulderGoal(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	@Override
	public boolean satisfied() {
		List<Switch> switchesOnMap = dungeon.getSwitches();
		int count = 0;
		for (Switch s : switchesOnMap) {
			// for all switches check if there is a boulder on it
			List<Entity> entityOnSwitch = dungeon.getEntities(s.getX(), s.getY());
			// count boulder on that grid X,Y
			for (Entity ent : entityOnSwitch) {
				if (ent.type() == EntityType.BOULDER) {
					System.out.println("Boulder on " + s.getX() + " " + s.getY());
					count++;
				} else if (entityOnSwitch.size() == 1 && ent.type() == EntityType.SWITCH) {
					System.out.println("No boulder on " + s.getX() + " " + s.getY());
				}
			}
		}
		System.out.println("Count size: " + count);
		if (count == switchesOnMap.size()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void print() {
		System.out.println("BOULDER GOAL");
	}
}
