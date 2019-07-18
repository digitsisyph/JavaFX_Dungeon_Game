package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class LitBombState3 implements LitBombState {

	private LitBomb bomb;

	LitBombState3(LitBomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public Image getImage() {
		return new Image("/bomb_lit_3.png");
	}

	@Override
	public void next() {
		bomb.setState(new LitBombState4(bomb));
	}

}
