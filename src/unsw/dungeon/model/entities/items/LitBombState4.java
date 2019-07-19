package unsw.dungeon.model.entities.items;

public class LitBombState4 implements LitBombState {

	private LitBomb bomb;

	LitBombState4(LitBomb bomb) {
		this.bomb = bomb;
		// in this state, the bomb explodes
		bomb.getDungeon().explodeBomb(bomb);
	}

	@Override
	public String getImagePath() {
		return "/bomb_lit_4.png";
	}

	@Override
	public void next() {
		// after this state, the bomb would be removed
		bomb.getDungeon().removeEntity(bomb);
	}
}
