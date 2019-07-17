package unsw.dungeon.entities.items;

import javafx.scene.image.Image;

public class UnlitBomb implements BombState {

	Bomb bomb;
	
	public UnlitBomb(Bomb bomb) {
		this.bomb = bomb;
	}
	
	@Override
	public Image getImage() {
		return new Image("/bomb_unlit.png");
	}

	@Override
	public boolean passThrough() {
		return true;
	}

	@Override
	public boolean next() {
		// does not have next state
		// because it is not lit
		return false;
	}

	@Override
	public void lit() {
		bomb.setState(bomb.getLit1());
	}

}
