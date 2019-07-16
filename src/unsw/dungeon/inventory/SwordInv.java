package unsw.dungeon.inventory;

import javafx.scene.image.Image;

class SwordInv {

    private int durability;

    SwordInv() {
        this.durability = 5;
    }

    void restoreDurability() {
        this.durability += 1;
    }

    public Image getImage() {
        // TODO Auto-generated method stub
        return new Image("/greatsword_1_new.png");
    }

    void use() {
        this.durability -= 1;
    }

    boolean broken() {
        if (this.durability <= 0)
            return true;
        else
            return false;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
