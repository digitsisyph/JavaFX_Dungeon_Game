package unsw.dungeon.model.entities.Bomb;

public class LitBombState3 implements LitBombState {

	private LitBomb bomb;

	LitBombState3(LitBomb bomb) {
		this.bomb = bomb;
		bomb.setImagePath(this.getImagePath());
	}

	@Override
	public String getImagePath() {
		return "/bomb_lit_3.png";
	}

	@Override
	public void nextState() {
		bomb.setState(new LitBombState4(bomb));
	}

}
