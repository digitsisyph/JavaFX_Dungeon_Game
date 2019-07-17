package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class LitBomb3 implements BombState {

	Bomb bomb;

	public LitBomb3(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public Image getImage() {
		return new Image("/bomb_lit_3.png");
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
