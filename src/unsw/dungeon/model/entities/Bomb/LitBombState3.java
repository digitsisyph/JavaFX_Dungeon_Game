package unsw.dungeon.model.entities.Bomb;

public class LitBombState3 implements LitBombState {

	private LitBomb bomb;

	LitBombState3(LitBomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public String getImagePath() {
		return "/bomb_lit_3.png";
	}

	@Override
	public void next() {
		bomb.setState(new LitBombState4(bomb));
	}

}
