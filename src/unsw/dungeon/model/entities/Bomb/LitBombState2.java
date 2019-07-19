package unsw.dungeon.model.entities.Bomb;

public class LitBombState2 implements LitBombState {

	private LitBomb bomb;

	LitBombState2(LitBomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public String getImagePath() {
		return "/bomb_lit_2.png";
	}

	@Override
	public void next() {
		bomb.setState(new LitBombState3(bomb));
		bomb.getDungeon().updateEntityImage(bomb);
	}

}
