package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class LitBomb4 implements BombState {

	Bomb bomb;

	public LitBomb4(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public Image getImage() {
		return new Image("/bomb_lit_4.png");
	}

	@Override
	public boolean passThrough() {
		return false;
	}

	@Override
	public void next() {
		// TODO Should be deleted
	}

	@Override
	public void lit() {
		// last phase
	}

}
