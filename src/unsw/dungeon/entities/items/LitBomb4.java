package unsw.dungeon.entities.items;

public class LitBomb4 implements BombState {

	Bomb bomb;

	public LitBomb4(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public String getImage() {
		return "/bomb_lit_4.png";
	}

	@Override
	public boolean passThrough() {
		return false;
	}

	@Override
	public boolean next() {
		System.out.println("EXPLOSION");
		bomb.getDungeon().bombActivated(bomb);
		return false;
	}

	@Override
	public void lit() {
		// last phase
	}

}
