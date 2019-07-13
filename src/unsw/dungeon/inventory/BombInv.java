package unsw.dungeon.inventory;

public class BombInv {

    private int num_bombs;

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
