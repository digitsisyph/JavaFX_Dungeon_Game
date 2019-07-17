package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class LitBomb1 implements BombState {

	Bomb bomb;

	public LitBomb1(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public Image getImage() {
		return new Image("/bomb_lit_1.png");
	}

	@Override
	public boolean passThrough() {
		return false;
	}

	@Override
	public void next() {
		bomb.setState(bomb.getLit2());
	}

	@Override
	public void lit() {
		/*
		 * Cannot be lit because it is already lit
		 */
	}

}
