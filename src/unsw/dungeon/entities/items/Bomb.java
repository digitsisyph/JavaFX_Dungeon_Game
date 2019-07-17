package unsw.dungeon.entities.items;

import javafx.scene.image.Image;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.EntityType;
import unsw.dungeon.entities.movable.Player;

public class Bomb extends Entity {

	private BombState unlit;
	private BombState lit1;
	private BombState lit2;
	private BombState lit3;
	private BombState lit4;
	private BombState state;

	public Bomb(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		unlit = new UnlitBomb(this);
		lit1 = new LitBomb1(this);
		lit2 = new LitBomb2(this);
		lit3 = new LitBomb3(this);
		lit4 = new LitBomb4(this);
		this.state = unlit;
		this.setPassThrough(state.passThrough());
	}

	@Override
	public Image getImage() {
		return state.getImage();
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			if (this.state == unlit) {
				this.getDungeon().pickUpBomb(this);
			}
		}
	}

	@Override
	public EntityType type() {
		return EntityType.BOMB;
	}

	public void lit() {
		state.lit();
		this.setPassThrough(state.passThrough());
	}

	public boolean next() {
		return state.next();
	}

	/**
	 * @return the state
	 */
	public BombState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(BombState state) {
		this.state = state;
	}

	/**
	 * @return the lit1
	 */
	public BombState getLit1() {
		return lit1;
	}

	/**
	 * @return the lit2
	 */
	public BombState getLit2() {
		return lit2;
	}

	/**
	 * @return the lit3
	 */
	public BombState getLit3() {
		return lit3;
	}

	/**
	 * @return the lit4
	 */
	public BombState getLit4() {
		return lit4;
	}

	@Override
	public String toString() {
		return "Bomb state=" + state + "]";
	}

}
