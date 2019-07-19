package unsw.dungeon.model.entities.CollisionStrategy;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.Player;

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
