package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;

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
