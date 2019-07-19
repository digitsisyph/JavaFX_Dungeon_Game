package unsw.dungeon.entities.CollisionStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;

class CollisionPush {
    private Entity entity;

    CollisionPush(Entity entity) {
        this.entity = entity;
    }

    // nothing should happen
    public void collideWith(Entity collidedEntity, Dungeon dungeon) {;}
}
