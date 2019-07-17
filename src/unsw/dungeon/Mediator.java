package unsw.dungeon;

import java.util.List;

import unsw.dungeon.entities.Entity;

// make this singleton later

public class Mediator {
	private Dungeon dungeon;

	public Mediator(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	public void removeEntity(Entity entity) {
		dungeon.removeEntity(entity);
	}
	
	public List<Entity> getEntitiesAt(int x,int y){
		return dungeon.getEntities(x, y);
	}
	
	public Boolean canOccupyGrid(int x,int y) {
		return dungeon.canOccupyGrid(x, y);
	}
	
	public void playerMovementTick() {
		dungeon.playerMovementUpdate();
	}
	
}
