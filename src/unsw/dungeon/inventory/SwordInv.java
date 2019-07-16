package unsw.dungeon.inventory;

import javafx.scene.image.Image;

public class SwordInv {

    private int durability;

    void restoreDurability() {
        this.durability += 1;
    }

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
