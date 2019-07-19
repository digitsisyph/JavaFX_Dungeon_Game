package unsw.dungeon.entities.CollisionStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.movable.Player;

class CollisionPickUp {
    private Entity entity;

    CollisionPickUp(Entity entity) {
        this.entity = entity;
    }

    // pickup this entity
    public void collideWith(Entity collidedEntity, Dungeon dungeon) {
        if (collidedEntity instanceof Player)
            dungeon.pickUp(this.entity);
    }
}
