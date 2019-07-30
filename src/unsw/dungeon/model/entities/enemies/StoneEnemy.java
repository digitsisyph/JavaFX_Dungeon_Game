package unsw.dungeon.model.entities.enemies;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Movable;

public class StoneEnemy extends Enemy implements Movable {

    public StoneEnemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.setImagePath("/stone_monster.png");
    }

    public void updatePerMovement() {
        // not move
    }

}
