package unsw.dungeon.entities.items;

public class LitBomb3 implements BombState {

	Bomb bomb;

	public LitBomb3(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public String getImage() {
		return "/bomb_lit_3.png";
	}

	@Override
	public boolean passThrough() {
		return false;
	}

	@Override
	public boolean next() {
		System.out.println("Bomb now lit4");
		bomb.setState(bomb.getLit4());
		return true;
	}

	@Override
	public void lit() {
		/*
		 * Cannot be lit because it is already lit
		 */
	}

}
