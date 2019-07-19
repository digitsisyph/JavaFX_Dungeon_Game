package unsw.dungeon.model.entities.Bomb;

public class LitBombState4 implements LitBombState {

	private LitBomb bomb;

	LitBombState4(LitBomb bomb) {
		this.bomb = bomb;
		// in this state, the bomb explodes
		bomb.getDungeon().explodeBomb(bomb);
		bomb.setImagePath(this.getImagePath());
	}

	@Override
	public String getImagePath() {
		return "/bomb_lit_4.png";
	}

	@Override
	public void nextState() {
		// after this state, the bomb would be removed
		bomb.getDungeon().removeEntity(bomb);
	}
}
