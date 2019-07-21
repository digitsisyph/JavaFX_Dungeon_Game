package unsw.dungeon.model.inventory;

class SwordInv {

    private int durability;

    SwordInv() {
        this.durability = 5;
    }

    int getDurability() {
        return durability;
    }

    void restoreDurability() {
        this.durability = 5;
    }

    void use() {
        this.durability -= 1;
    }

    boolean broken() {
        return this.durability <= 0;
    }

}
