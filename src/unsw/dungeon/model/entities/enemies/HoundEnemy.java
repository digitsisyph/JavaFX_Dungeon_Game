package unsw.dungeon.model.entities.enemies;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.*;

public class HoundEnemy extends Enemy implements Movable {

    private EnemyBehaviour moveClose = new EnemyMoveCloseStupid();
    private EnemyBehaviour moveAway = new EnemyMoveAway();

    public HoundEnemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.setImagePath("/hound.png");
        this.setBehaviour(moveClose);
    }

    public void updatePerMovement() {
        // set strategy
        if (getDungeon().isPlayerInvincible())
            this.setBehaviour(moveAway);
        else
            this.setBehaviour(moveClose);
        // move
        this.move(getDungeon().getPlayer());
    }

}
