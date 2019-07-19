package unsw.dungeon.model.inventory;

class BombInv {

    private int num_bombs = 0;

    void increaseBomb() {
        this.num_bombs += 1;
    }

    void decreaseBomb() {
        this.num_bombs -= 1;
    }

    int getNumBombs() {
        return num_bombs;
    }
}
