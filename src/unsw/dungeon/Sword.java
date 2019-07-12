package unsw.dungeon;

import javafx.scene.image.Image;

public class Sword extends Entity {

	private int durability;

	public Sword(int x, int y) {
		super(x, y);
		this.durability = 5; // default to 5
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return new Image("/greatsword_1_new.png");
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

}
