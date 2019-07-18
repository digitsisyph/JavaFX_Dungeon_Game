package unsw.dungeon.inventory;

class TreasureInv {
    private int num_treasures;

    TreasureInv() {
        this.num_treasures = 0;
    }

    public void pickTreasure() {
        this.num_treasures++;
    }
    
    public int getNumTreasures() {
    	return this.num_treasures;
    }
}
