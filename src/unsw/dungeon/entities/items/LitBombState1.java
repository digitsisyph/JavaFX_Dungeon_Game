package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class LitBombState1 implements LitBombState {

	private LitBomb bomb;

	LitBombState1(LitBomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public Image getImage() {
		return new Image("/bomb_lit_1.png");
	}

	@Override
	public void next() {
		bomb.setState(new LitBombState2(bomb));
	}

}
