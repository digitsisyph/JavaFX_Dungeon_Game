package unsw.dungeon.model.entities.Bomb;

public class LitBombState1 implements LitBombState {

	private LitBomb bomb;

	LitBombState1(LitBomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public String getImagePath() {
		return "/bomb_lit_1.png";
	}

	@Override
	public void next() {
		bomb.setState(new LitBombState2(bomb));
	}

}
