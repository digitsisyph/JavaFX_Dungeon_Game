package unsw.dungeon.model.entities.enemies;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.*;

public abstract class Enemy extends Entity implements Movable {

    Movement movement;
    EnemyBehaviour behaviour;

    Enemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.movement = new Movement(this, dungeon);
        this.setPassThrough(false);
    }

    public void setBehaviour(EnemyBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void move(Player player) {
        behaviour.move(this, player);
    }

    public void moveUp() {
        this.movement.moveUp();
    }

    public void moveDown() {
        this.movement.moveDown();
    }

    public void moveLeft() {
        this.movement.moveLeft();
    }

    public void moveRight() {
        this.movement.moveRight();
    }

    public void collideWith(Entity entity) {
        if (entity instanceof Player) {
            this.getDungeon().fightEnemy(this);
        }
    }

    public abstract void updateStrategy(boolean playerInvincible);

    @Override
    public EntityType type() {
        return EntityType.ENEMY;
    }

}
