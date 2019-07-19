package unsw.dungeon.model.entities.items;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;

public class LitBomb extends Entity {

	private LitBombState state;

	public LitBomb(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.state = new LitBombState1(this);
		this.setPassThrough(false);
	}

	@Override
	public String getImagePath() {
		return state.getImagePath();
	}

	public void collideWith(Entity entity) {;}

	@Override
	public EntityType type() {
		return EntityType.LITBOMB;
	}

	public void nextState() {
		state.next();
	}

	/**
	 * @return the state
	 */
	public LitBombState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(LitBombState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Bomb state=" + state + "]";
	}

}
