package unsw.dungeon.entities.items;

public class LitBomb2 implements BombState {

	Bomb bomb;

	public LitBomb2(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public String getImage() {
		return "/bomb_lit_2.png";
	}

	@Override
	public boolean passThrough() {
		return false;
	}

	@Override
	public boolean next() {
		System.out.println("Bomb now lit3");
		bomb.setState(bomb.getLit3());
		return true;
	}

	@Override
	public void lit() {
		/*
		 * Cannot be lit because it is already lit
		 */
	}

}
