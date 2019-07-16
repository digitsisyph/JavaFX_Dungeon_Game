package unsw.dungeon.inventory;

import unsw.dungeon.entities.items.Bomb;

public class Inventory {

    private BombInv bomb;
    private SwordInv sword;
    private KeyInv key;
    private TreasureInv treasure;

    public Inventory() {
        this.bomb = new BombInv();
        this.sword = null;
        this.key = null;
        this.treasure = null;
    }

    public void pickSword() {
        if (this.sword == null) {
            this.sword = new SwordInv();
        } else {
            this.sword.restoreDurability();
        }
    }

    public boolean useSword() {
        if (this.sword == null)
            return false;

        this.sword.use();
        if (this.sword.broken())
            this.sword = null;
        return true;
    }

    public void pickBomb() {
        this.bomb.increaseBomb();
    }

    public void useBomb() {
        this.bomb.decreaseBomb();
    }

    public void pickKey(int id) {
        this.key = new KeyInv(id);
    }

    public void useKey() {
        this.key = null;
    }
}
