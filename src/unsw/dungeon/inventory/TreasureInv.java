package unsw.dungeon.inventory;

import unsw.dungeon.entities.items.Treasure;

class TreasureInv {
    private int num_treasures;

    TreasureInv() {
        this.num_treasures = 0;
    }

    public void pickTreasure() {
        this.num_treasures++;
    }
}
