package unsw.dungeon.entities.items;

public class UnlitBomb implements BombState {

	Bomb bomb;
	
	public UnlitBomb(Bomb bomb) {
		this.bomb = bomb;
	}
	
	@Override
	public String getImage() {
		return "/bomb_unlit.png";
	}

	@Override
	public boolean passThrough() {
		return true;
	}

	@Override
	public boolean next() {
		// does not have next state
		// because it is not lit
		return false;
	}

	@Override
	public void lit() {
		bomb.setState(bomb.getLit1());
	}

}
