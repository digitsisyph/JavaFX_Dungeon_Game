package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class LitBombState2 implements LitBombState {

	private LitBomb bomb;

	LitBombState2(LitBomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public Image getImage() {
		return new Image("/bomb_lit_2.png");
	}

	@Override
	public void next() {
		bomb.setState(new LitBombState3(bomb));
	}

}
