package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class LitBomb2 implements BombState {

	Bomb bomb;

	public LitBomb2(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public Image getImage() {
		return new Image("/bomb_lit_2.png");
	}

	@Override
	public boolean passThrough() {
		return false;
	}

	@Override
	public void next() {
		System.out.println("Bomb now lit3");
		bomb.setState(bomb.getLit3());
	}

	@Override
	public void lit() {
		/*
		 * Cannot be lit because it is already lit
		 */
	}

}
