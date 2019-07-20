package unsw.dungeon.model.inventory;

public class InvincibleState {

    private int remaining_time;
    private Inventory inventory;

    InvincibleState(Inventory inventory, int remaining_time) {
        this.remaining_time = remaining_time;
        this.inventory = inventory;
    }

    void nextState() {
        if (remaining_time > 1) {
            inventory.setInvincibleState(new InvincibleState(inventory, remaining_time - 1));
        } else {
            inventory.setInvincibleState(null);
        }
    }

    public int getRemainingTime() {
        return this.remaining_time;
    }
}
